package com.example.p440;

public class Item {
    String name;
    String phone;
    // image는 resource에서  int로 관리되어짐.
    int imgId ;

    public Item() {
    }

    public Item(String name, String phone, int imgId) {
        this.name = name;
        this.phone = phone;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getImgId() {
        return imgId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
