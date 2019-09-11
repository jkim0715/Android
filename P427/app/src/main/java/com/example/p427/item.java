package com.example.p427;

public class item {
    String name;
    String phone;
    // image는 resource에서  int로 관리되어짐.
    String imgId ;

    public item() {
    }

    public item(String name, String phone, String imgId) {
        this.name = name;
        this.phone = phone;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
