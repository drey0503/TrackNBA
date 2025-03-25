package com.example.tracknba;

public class ChatListModel {
    private String message;
//    private String recievemessage;

    private ChatListModel(String message, String rMessage) {
        this.message = message;
//        this.recievemessage = rMessage;
    }

    private ChatListModel() {
    }

    public String getMessage() {
        return message;
    }
//
//    public String getRecievemessage(){
//        return recievemessage;
//    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public void setRecievemessage(String rmessage){
//        this.recievemessage=rmessage;
//    }
}
