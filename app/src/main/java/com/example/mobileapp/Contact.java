package com.example.mobileapp;

public class Contact {
    private String name;
    private String lastMessage;
    private String imageUrl;

    public Contact(String name, String lastMessage, String imageUrl) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
