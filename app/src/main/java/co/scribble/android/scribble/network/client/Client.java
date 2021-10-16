package co.scribble.android.scribble.network.client;

import co.scribble.android.scribble.network.constant.N;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit = null;

    public static Retrofit get() {
        if (retrofit != null) return retrofit;

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(N.BASE_URL)
                .build();

        return retrofit;
    }
}
