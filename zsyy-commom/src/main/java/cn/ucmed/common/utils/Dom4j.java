package cn.ucmed.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 解析xml
 */
public class Dom4j {

    /**
     * 转换一个xml格式的字符串到json格式
     */
    public static JSONObject xml2JSON(String xml){
        JSONObject obj = new JSONObject();
        try {
            Document doc = DocumentHelper.parseText(xml);
            Element root = doc.getRootElement();
            obj.put(root.getName(), iterateElement(root));
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map iterateElement(Element element) {
        List jiedian = element.elements();
        Element et;
        Map obj = new HashMap();
        Object temp;
        List list;
        for (Object o : jiedian) {
            list = new LinkedList();
            et = (Element) o;
            if (et.getTextTrim().equals("")) {
                if (et.elements().size() == 0)
                    continue;
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if (temp instanceof List) {
                        list = (List) temp;
                        list.add(iterateElement(et));
                    } else if (temp instanceof Map) {
                        list.add(temp);
                        list.add(iterateElement(et));
                    } else {
                        list.add(temp);
                        list.add(iterateElement(et));
                    }
                    obj.put(et.getName(), list);
                } else {
                    obj.put(et.getName(), iterateElement(et));
                }
            } else {
                if (obj.containsKey(et.getName())) {
                    temp = obj.get(et.getName());
                    if (temp instanceof List) {
                        list = (List) temp;
                        list.add(et.getTextTrim());
                    } else if (temp instanceof Map) {
                        list.add(temp);
                        list.add(iterateElement(et));
                    } else {
                        list.add(temp);
                        list.add(et.getTextTrim());
                    }
                    obj.put(et.getName(), list);
                } else {
                    obj.put(et.getName(), et.getTextTrim());
                }

            }

        }
        return obj;
    }

}
