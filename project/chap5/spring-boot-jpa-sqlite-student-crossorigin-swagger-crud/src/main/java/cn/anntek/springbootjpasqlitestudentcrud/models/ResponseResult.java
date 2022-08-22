package cn.anntek.springbootjpasqlitestudentcrud.models;

public class ResponseResult {
    private boolean success;
    private Object data;
    private String message;


    public ResponseResult(){
        this.success=false;
        this.data=null;
        this.message="";
    }

    public ResponseResult(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public void setStatus(ResponseStatus status){
        this.message=status.getMessage();
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
