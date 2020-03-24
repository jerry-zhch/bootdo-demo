package cn.ucmed.common.api;

import cn.ucmed.common.utils.result.Result;
import com.alibaba.fastjson.JSONObject;

public interface Api {

    Result exec(JSONObject params);

}
