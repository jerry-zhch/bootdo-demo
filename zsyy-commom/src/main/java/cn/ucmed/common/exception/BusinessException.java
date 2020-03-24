package cn.ucmed.common.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 7987377892977933890L;

    /**
     * 错误码，用来和客户端约定接收到异常时的行为
     */
    private Integer code;

    private String message;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessException that = (BusinessException) o;

        if (!code.equals(that.code)) return false;
        return message.equals(that.message);

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

}
