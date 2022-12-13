package co.scribble.android.scribble.ui.write.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.network.client.Client;
import co.scribble.android.scribble.network.model.BaseResponse;
import co.scribble.android.scribble.network.model.CreateStory;
import co.scribble.android.scribble.network.services.UserStories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateStoryViewModel extends ViewModel {
    private final UserStories stories;
    private MutableLiveData<String> response = null;

    public CreateStoryViewModel() {
        stories = Client.get().create(UserStories.class);
    }

    public MutableLiveData<String> createResponse() {
        if (response != null) return response;
        return response = new MutableLiveData<>();
    }

    public void createStory(CreateStory story) {
        Call<BaseResponse> call = stories.createStory(story);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(@NonNull Call<BaseResponse> call, @NonNull Response<BaseResponse> res) {
                if (res.body() != null)
                    response.setValue(res.body().getMessage());
            }

            @Override
            public void onFailure(@NonNull Call<BaseResponse> call, @NonNull Throwable t) {
                response.setValue(C.SERVER_ERR);
            }
        });
    }
}
