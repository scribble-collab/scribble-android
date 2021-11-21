package co.scribble.android.scribble.ui.feed.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.scribble.android.scribble.models.Story;
import co.scribble.android.scribble.network.client.Client;
import co.scribble.android.scribble.network.services.UserStories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedStoryViewModel extends ViewModel {
    private final UserStories userStories;
    private MutableLiveData<List<Story>> stories = null;

    public FeedStoryViewModel () {
        userStories = Client.get().create(UserStories.class);
    }

    public MutableLiveData<List<Story>> storyResponse () {
        if (stories != null) return stories;
        return stories = new MutableLiveData<>();
    }

    public void fetchStories(String type, String sort, int offset, int limit) {
        Call<List<Story>> call = userStories.getStories(type, sort, offset, limit);
        call.enqueue(new Callback<List<Story>>() {
            @Override
            public void onResponse(@NonNull Call<List<Story>> call, @NonNull Response<List<Story>> response) {
                if(response.isSuccessful())
                    stories.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Story>> call, @NonNull Throwable t) {

            }
        });
    }
}
