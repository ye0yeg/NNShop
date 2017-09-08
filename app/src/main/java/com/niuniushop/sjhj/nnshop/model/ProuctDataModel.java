package com.niuniushop.sjhj.nnshop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/9/8.
 */

public class ProuctDataModel implements Parcelable{

    private int id;

    private String name;

    private float price;

    private int type;

    private String img;

    private int carrieroperator;

    private int color;

    private int storage;

    protected ProuctDataModel(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readFloat();
        type = in.readInt();
        img = in.readString();
        carrieroperator = in.readInt();
        color = in.readInt();
        storage = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeFloat(price);
        dest.writeInt(type);
        dest.writeString(img);
        dest.writeInt(carrieroperator);
        dest.writeInt(color);
        dest.writeInt(storage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<ProuctDataModel> CREATOR = new Parcelable.Creator<ProuctDataModel>() {
        @Override
        public ProuctDataModel createFromParcel(Parcel in) {
            return new ProuctDataModel(in);
        }

        @Override
        public ProuctDataModel[] newArray(int size) {
            return new ProuctDataModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getCarrieroperator() {
        return carrieroperator;
    }

    public void setCarrieroperator(int carrieroperator) {
        this.carrieroperator = carrieroperator;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }
}
