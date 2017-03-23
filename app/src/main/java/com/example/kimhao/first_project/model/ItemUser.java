package com.example.kimhao.first_project.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KimHao on 13/03/2017.
 */

public class ItemUser extends ItemList implements Parcelable {
    String id;
    String Name, Addr;
    String ImgAva;
    String Age;
    boolean isCheck;
    int ImgTrip;
    int ImgRibbon;

    public ItemUser() {
    }

    public ItemUser(String imgAva, String name, String age, String addr) {
        Name = name;
        Addr = addr;
        ImgAva = imgAva;
        Age = age;
    }

    public ItemUser(String id, String imgAva, String name, String age, String addr) {
        this.id = id;
        Name = name;
        Addr = addr;
        ImgAva = imgAva;
        Age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddr() {
        return Addr;
    }

    public void setAddr(String addr) {
        Addr = addr;
    }

    public String getImgAva() {
        return ImgAva;
    }

    public void setImgAva(String imgAva) {
        ImgAva = imgAva;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getImgTrip() {
        return ImgTrip;
    }

    public void setImgTrip(int imgTrip) {
        ImgTrip = imgTrip;
    }

    public int getImgRibbon() {
        return ImgRibbon;
    }

    public void setImgRibbon(int imgRibbon) {
        ImgRibbon = imgRibbon;
    }

    public static Creator<ItemUser> getCREATOR() {
        return CREATOR;
    }

    protected ItemUser(Parcel in) {
        id = in.readString();
        Name = in.readString();
        Addr = in.readString();
        ImgAva = in.readString();
        Age = in.readString();
        isCheck = in.readByte() != 0;
        ImgTrip = in.readInt();
        ImgRibbon = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Name);
        dest.writeString(Addr);
        dest.writeString(ImgAva);
        dest.writeString(Age);
        dest.writeByte((byte) (isCheck ? 1 : 0));
        dest.writeInt(ImgTrip);
        dest.writeInt(ImgRibbon);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ItemUser> CREATOR = new Creator<ItemUser>() {
        @Override
        public ItemUser createFromParcel(Parcel in) {
            return new ItemUser(in);
        }

        @Override
        public ItemUser[] newArray(int size) {
            return new ItemUser[size];
        }
    };

    @Override
    public int getType() {
        return 2;
    }
}
