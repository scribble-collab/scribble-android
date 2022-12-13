package co.scribble.android.scribble.ui.auth.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.models.Auth;
import co.scribble.android.scribble.network.client.Client;
import co.scribble.android.scribble.network.services.UserAuth;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {
    private final UserAuth userAuth;
    private MutableLiveData<Auth> auth = null;
    private MutableLiveData<Auth> loginAuth = null;
    private MutableLiveData<String> msgRes = null;

    public AuthViewModel() {
        userAuth = Client.get().create(UserAuth.class);
    }

    public MutableLiveData<Auth> registerResponse() {
        if (auth != null) return auth;
        return auth = new MutableLiveData<>();
    }

    public MutableLiveData<Auth> loginResponse() {
        if (loginAuth != null) return loginAuth;
        return loginAuth = new MutableLiveData<>();
    }

    public MutableLiveData<String> msgResponse() {
        if (msgRes != null) return msgRes;
        return msgRes = new MutableLiveData<>();
    }

    public void registerUser(String username, String password) {
        Auth body = new Auth();
        body.setUsername(username);
        body.setPassword(password);

        Call<Auth> call = userAuth.signupUser(body);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NonNull Call<Auth> call, @NonNull Response<Auth> response) {
                if (response.isSuccessful()) {
                    auth.setValue(response.body());
                }
                if (response.code() == 403) {
                    msgRes.setValue("Username is already taken");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Auth> call, @NonNull Throwable t) {
                msgRes.setValue(C.SERVER_ERR);
            }
        });
    }

    public void loginUser(String username, String password) {
        Auth body = new Auth();
        body.setUsername(username);
        body.setPassword(password);

        Call<Auth> call = userAuth.loginUser(body);
        call.enqueue(new Callback<Auth>() {
            @Override
            public void onResponse(@NonNull Call<Auth> call, @NonNull Response<Auth> response) {
                if (response.isSuccessful()) {
                    loginAuth.setValue(response.body());
                } else if (response.code() == 401) {
                    msgRes.setValue("Invalid credentials");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Auth> call, @NonNull Throwable t) {
                msgRes.setValue(C.SERVER_ERR);
            }
        });
    }
}
