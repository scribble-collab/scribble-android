package co.scribble.android.scribble.network.services;

import co.scribble.android.scribble.models.Auth;
import co.scribble.android.scribble.network.constant.N;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAuth {
    @POST(N.AUTH_USER_LOGIN)
    Call<Auth> loginUser(@Body Auth auth);

    @POST(N.AUTH_USER_SIGNUP)
    Call<Auth> signupUser(@Body Auth auth);
}
