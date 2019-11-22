package com.example.intentapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestModel implements Parcelable {
    private String mString1 ;
    private String mString2 ;
    private List<Integer> mObjectList1;
    private List<Integer> mObjectList2;

    public TestModel() {
        Random random = new Random();
        mString1 = "";
        mString2 = "";
        mObjectList1 = new ArrayList<>();
        mObjectList2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mString1 += (char) (random.nextInt(26) + 'a');
            mString2 += (char) (random.nextInt(26) + 'a');
            mObjectList1.add(random.nextInt(26));
            mObjectList2.add(random.nextInt(26));
        }
        mString1 += "\n";
        mString2 += "\n";


    }

    protected TestModel(Parcel in) {
        mString1 = in.readString();
        mString2 = in.readString();
        mObjectList1 = in.readArrayList(TestModel.class.getClassLoader());
        mObjectList2 = in.readArrayList(TestModel.class.getClassLoader());
    }

    public String getString1() {
        return mString1;
    }


    public String getString2() {
        return mString2;
    }


    public List<Integer> getObjectList1() {
        return mObjectList1;
    }


    public List<Integer> getObjectList2() {
        return mObjectList2;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mString1);
        dest.writeString(mString2);
        dest.writeList(mObjectList1);
        dest.writeList(mObjectList2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TestModel> CREATOR = new Creator<TestModel>() {
        @Override
        public TestModel createFromParcel(Parcel in) {
            return new TestModel(in);
        }

        @Override
        public TestModel[] newArray(int size) {
            return new TestModel[size];
        }
    };
}
