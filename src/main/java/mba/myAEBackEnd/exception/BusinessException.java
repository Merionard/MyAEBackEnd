package mba.myAEBackEnd.exception;

public class BusinessException extends Throwable{

    private String msg;
    public BusinessException(String msg){
        super(msg);
        this.msg = msg;
    }
}
