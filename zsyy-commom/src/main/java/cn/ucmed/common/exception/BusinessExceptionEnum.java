package cn.ucmed.common.exception;

public enum BusinessExceptionEnum {

    ILLEGAL_HOSPITAL(714, "illegal hospital"),

    DYNAMIC_FUNC_NOT_FOUND(715, "dynamic func not found"),

    CONVERT_EXCEPTION(716, "convert dto to po exception");

    private String message;
    private int code;

    BusinessExceptionEnum(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
