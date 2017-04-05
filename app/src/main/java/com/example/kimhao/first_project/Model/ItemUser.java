package com.example.kimhao.first_project.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by KimHao on 13/03/2017.
 */

public class ItemUser extends ItemList implements Parcelable {
    private int id;
    private String image;
    private String name;
    private String age;
    private String address;
    private int favorite;
    private boolean isFavorite;
    private final int VIEW_ITEM = 1;

    public ItemUser(String image, String name, String age, String address, int favorite, boolean isFavorite) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.address = address;
        this.favorite = favorite;
        this.isFavorite = isFavorite;
    }

    public ItemUser() {

    }

    public ItemUser(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public ItemUser(String image, String name, String age, String address) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public ItemUser(int id, String image, String name, String age, String address) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    protected ItemUser(Parcel in) {
        id = in.readInt();
        image = in.readString();
        name = in.readString();
        age = in.readString();
        address = in.readString();
        favorite = in.readInt();
        isFavorite = in.readByte() != 0;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image);
        dest.writeString(name);
        dest.writeString(age);
        dest.writeString(address);
        dest.writeInt(favorite);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int getType() {
        return VIEW_ITEM;
    }

}
