package com.example.gremaster;

public class Answers {

    private String name, profileImage, answer, time, date, key;
    public Answers(){

    }
    public Answers(String name, String profileImage, String answer, String time, String date) {
        this.name = name;
        this.profileImage = profileImage;
        this.answer = answer;
        this.time = time;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String question) {
        this.answer = question;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
