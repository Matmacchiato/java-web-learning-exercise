package cn.anntek.springbootjpasqlitestudentcrud.models;

public enum ResponseStatus {
    SUCCESS(200,"请求成功"),
    CREATED(201,"新增成功"),
    UPDATED(203,"修改成功"),
    DELETED(204,"删除成功"),
    ERROR_400(400,"数据验证错误"),
    ERROR_401(401,"无权限"),
    ERROR_404(404,"资源不存在"),
    ERROR_500(500,"服务端错");


    private int code;
    private String message;

    ResponseStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
