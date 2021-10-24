package co.scribble.android.scribble.network.client;

import co.scribble.android.scribble.App;
import co.scribble.android.scribble.helpers.Pref;
import co.scribble.android.scribble.network.constant.N;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    private static Retrofit retrofit = null;

    public static Retrofit get() {
        if (retrofit != null) return retrofit;

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(chain -> {
            Request request = chain
                    .request()
                    .newBuilder()
                    .addHeader("authorization", Pref.getAuthToken(App.getContext()))
                    .build();
            return chain.proceed(request);
        });

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(N.BASE_URL)
                .client(client.build())
                .build();

        return retrofit;
    }
}
