package id.putraprima.skorbola;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private String homeName;
    private String awayName;

    public Data(String homeName, String awayName) {
        this.homeName = homeName;
        this.awayName = awayName;
    }

    protected Data(Parcel in) {
        homeName = in.readString();
        awayName = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public String getAwayName() {
        return awayName;
    }

    public void setAwayName(String awayName) {
        this.awayName = awayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(homeName);
        dest.writeString(awayName);
    }
}
