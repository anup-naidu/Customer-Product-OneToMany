package com.javatechie.jpa.exception;

public class CustomRestException extends Exception {

    private static final long serialVersionUID = 1223255356788L;

    final String generalMessage;
    final String devMessage;

    public CustomRestException(String generalMessage) {
        super(String.format("GeneralMessage[%s]",generalMessage));
        this.generalMessage=generalMessage;
        this.devMessage=null;
    }

    public CustomRestException(String generalMessage, String devMessage) {
        super(String.format("GeneralMessage[%s]","DevMessage[%s]",generalMessage,devMessage));
        this.generalMessage=generalMessage;
        this.devMessage=devMessage;

    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }


}
