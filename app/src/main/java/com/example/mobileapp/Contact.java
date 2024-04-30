package com.example.mobileapp;

public class Contact {
    private String name;
    private String lastMessage;
    private int resourceId;

    public Contact(String name, String lastMessage, int resourceId) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
