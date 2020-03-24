package cn.ucmed.common.utils.result;

public class ResultUtils {

    static final int SUCCESS_CODE = 200;
    static final String SUCCESS_VALUE = "操作成功";

    /**
     * 成功带数据和成功信息
     */
    public static Result success(String msg,Object object){
        return back(SUCCESS_CODE,msg,object);
    }

    /**
     * 成功带数据
     */
    public static Result success(Object object){
        return success(SUCCESS_VALUE,object);
    }

    /**
     * 成功无数据
     */
    public static Result success(){
        return success(null);
    }

    /**
     * 错误返回
     */
    public static Result error(Integer code,String msg){
        return back(code,msg,null);
    }

    /**
     * 错误返回
     */
    public static Result error(Integer code, String msg, Object data) {
        return back(code, msg, data);
    }

    /**
     * 错误返回
     */
    public static Result error(String msg){
        return back(500,msg,null);
    }

    /**
     * 全部参数
     */
    public static Result back(Integer code,String msg,Object data){
        return new Result(code,msg,data);
    }
}
