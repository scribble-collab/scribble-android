package co.scribble.android.scribble.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import co.scribble.android.scribble.C;

public class Pref {
    private static SharedPreferences.Editor editor(Context context) {
        return context.getSharedPreferences(C.PREF_KEY, Context.MODE_PRIVATE).edit();
    }

    private static SharedPreferences store(Context context) {
        return context.getSharedPreferences(C.PREF_KEY, Context.MODE_PRIVATE);
    }

    public static void saveUser(Context context, String userId) {
        editor(context).putString(C.PREF_USERID, userId).apply();
    }

    public static String getUser(Context context) {
        return store(context).getString(C.PREF_USERID, null);
    }

    public static void authToken(Context context, String authToken) {
        editor(context).putString(C.PREF_AUTH_TOKEN, authToken).apply();
    }

    public static String getAuthToken(Context context) {
        return store(context).getString(C.PREF_AUTH_TOKEN, null);
    }
}
