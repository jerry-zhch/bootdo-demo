package cn.ucmed.common.db.hospital.service.impl;

import cn.ucmed.common.db.hospital.entity.SysHospital;
import cn.ucmed.common.db.hospital.mapper.SysHospitalMapper;
import cn.ucmed.common.db.hospital.service.ISysHospitalService;
import cn.ucmed.common.db.system.entity.Tree;
import cn.ucmed.common.utils.BuildTree;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @auther 郄彦腾
 * @create 2019-11-29 11:38:19
 * @describe 医院管理表服务实现类
 */
@Service
public class SysHospitalServiceImpl extends ServiceImpl<SysHospitalMapper, SysHospital> implements ISysHospitalService {

    public static final Logger log = LoggerFactory.getLogger(SysHospitalServiceImpl.class);
    @Autowired
    private SysHospitalMapper hospitalMapper;

    @Override
    public List<SysHospital> getUserHospitals(Long userId) {
        return hospitalMapper.getUserHospitals(userId);
    }

    @Override
    public Tree<SysHospital> getTree(Long userId) {
        Set<String> set = new HashSet<>();
        List<Tree<SysHospital>> trees = new ArrayList<Tree<SysHospital>>();
        List<SysHospital> hospitals = hospitalMapper.getUserHospitals(userId);
        getTree(set, trees, hospitals);
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.build(trees);
    }

    @Override
    public Tree<SysHospital> getTreeSuper() {
        Set<String> set = new HashSet<>();
        List<Tree<SysHospital>> trees = new ArrayList<Tree<SysHospital>>();
        List<SysHospital> hospitals = hospitalMapper.selectList(new QueryWrapper<SysHospital>().orderByAsc("create_time"));
        getTree(set, trees, hospitals);
        // 默认顶级菜单为０，根据数据库实际情况调整
        return BuildTree.build(trees);
    }

    @Override
    public Tree<JSONObject> getTree() {
        List<Tree<JSONObject>> trees = new ArrayList<Tree<JSONObject>>();
        List<JSONObject> list = getYHHospilal();
        for (Object object : list) {
            JSONObject hospital = JSON.parseObject(object.toString());
            Tree<JSONObject> tree = new Tree<JSONObject>();
            tree.setId(hospital.getString("HospitalId"));
            tree.setParentId("0");
            tree.setText(hospital.getString("AllHosName"));
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<JSONObject> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public JSONArray getHospitalList(String type) {
        try{
            List<SysHospital> list = hospitalMapper.selectList(new QueryWrapper<SysHospital>().orderByAsc("seq"));
            JSONArray array = new JSONArray();
            for(SysHospital hospital: list) {
                JSONObject jsonObject = new JSONObject();
                if("0".equals(hospital.getParentId())) {
                    jsonObject.put("hosId",hospital.getHospitalId());
                    jsonObject.put("hosName",hospital.getHospitalName());
                    jsonObject.put("hosList",new JSONArray());
                    array.add(jsonObject);
                }
            }
            JSONArray hospitalList = new JSONArray();
            for(Object o: array) {
                JSONObject data = JSONObject.parseObject(JSON.toJSONString(o));
                String hosId = data.getString("hosId");
                JSONArray hosList = data.getJSONArray("hosList");
                for(SysHospital hospital: list) {
                    if(hosId.equals(hospital.getParentId())) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("hosId",hospital.getHospitalId());
                        jsonObject.put("hosName",hospital.getHospitalName());
                        JSONObject extend = JSONObject.parseObject(hospital.getExtend());
                        if("0".equals(extend.getString("qyHIS"))) {
                            //不是
                            jsonObject.put("qyHIS","0");
                        }else {
                            //是
                            if("order".equals(type) || "bin".equals(type)) {
                                continue;
                            }
                            jsonObject.put("qyHIS","1");
                        }
                        hosList.add(jsonObject);
                    }
                }
                data.put("hosList",hosList);
                hospitalList.add(data);
            }
            log.info("获取区域医院列表成功");
            return hospitalList;
        }catch (Exception e){
            log.info("获取区域医院列表失败");
        }
        return null;
    }

    private void getTree(Set<String> set, List<Tree<SysHospital>> trees, List<SysHospital> hospitals) {
        for (SysHospital hospital : hospitals) {
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            // 医院添加
            Tree<SysHospital> tree = new Tree<SysHospital>();
            tree.setId(hospital.getHospitalId());
            tree.setParentId(hospital.getParentId());
            tree.setText(hospital.getHospitalName());
            tree.setState(state);
            if (set.add(hospital.getHospitalId())) {
                trees.add(tree);
            }
        }
    }

    /**
     * 获取互联网医院信息
     * @return
     */
    public List<JSONObject> getYHHospilal() {
        List<JSONObject> list = new ArrayList<>();
        String result = sendGet("https://wx.jkwlmq.cn/pt/api/DjHospital/GetHospitalList?pagesize=100&pageno=1");
        JSONObject hospital = JSON.parseObject(result);
        int retCode = hospital.getInteger("ret_code");
        if(retCode != 0) {
           return  list;
        }
        for(Object o : hospital.getJSONObject("ret_data").getJSONArray("list")) {
            JSONObject object = JSON.parseObject(o.toString());
            list.add(object);
        }
        return list;
    }

    public static String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url;
            log.info("入参:<======".concat(urlNameString));
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("appid", "hyzxht2cfa78f19479027b244a09aee4542c");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for(String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义BufferedReader输入流来读取URL的响应
            if(connection.getResponseCode() >= 400) {
                in = new BufferedReader(new InputStreamReader(
                        connection.getErrorStream(), StandardCharsets.UTF_8));
            } else {
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream(), StandardCharsets.UTF_8));
            }
            String line;
            while((line = in.readLine()) != null) {
                result += line;
            }
        } catch(Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch(Exception e2) {
                e2.printStackTrace();
            }
        }
        log.info("出参:======>".concat(result));
        return result;
    }
}
