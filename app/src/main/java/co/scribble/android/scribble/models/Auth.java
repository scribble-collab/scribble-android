package co.scribble.android.scribble.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Auth implements Parcelable {
    public static final Creator<Auth> CREATOR = new Creator<Auth>() {
        @Override
        public Auth createFromParcel(Parcel in) {
            return new Auth(in);
        }

        @Override
        public Auth[] newArray(int size) {
            return new Auth[size];
        }
    };
    private String authToken;
    private String userId;
    private String username;
    private String password;
    private String status;

    public Auth() {
    }

    protected Auth(Parcel in) {
        authToken = in.readString();
        userId = in.readString();
        username = in.readString();
        password = in.readString();
    }

    public static Creator<Auth> getCREATOR() {
        return CREATOR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(authToken);
        parcel.writeString(userId);
        parcel.writeString(username);
        parcel.writeString(password);
    }
}
