package cn.ucmed.common.utils.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "返回结果")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 62953764968872521L;

    static final int SUCCESS_CODE = 200;

    @ApiModelProperty(value = "状态码",dataType = "Integer")
    private Integer code;

    @ApiModelProperty(value = "信息",dataType = "String")
    private String message;

    @ApiModelProperty(value = "封装结果")
    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return code == SUCCESS_CODE;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
