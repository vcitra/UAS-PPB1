package com.example.uas.Memo;

import android.os.Parcelable;
import android.os.Parcel;

public class Komentar implements Parcelable {
    private String id;
    private String nama;
    private String komen;

    public Komentar() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKomen() {
        return komen;
    }

    public void setKomen(String komen) {
        this.komen = komen;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.komen);
        dest.writeString(this.nama);
    }

    protected Komentar(Parcel in) {
        this.id = in.readString();
        this.komen = in.readString();
        this.nama = in.readString();
    }

    public static final Parcelable.Creator<Komentar> CREATOR = new Parcelable.Creator<Komentar>() {
        @Override
        public Komentar createFromParcel(Parcel source) {
            return new Komentar(source);
        }

        @Override
        public Komentar[] newArray(int size) {
            return new Komentar[size];
        }
    };
}
