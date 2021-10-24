package co.scribble.android.scribble.network.services;

import java.util.List;

import co.scribble.android.scribble.models.Story;
import co.scribble.android.scribble.network.constant.N;
import co.scribble.android.scribble.network.model.BaseResponse;
import co.scribble.android.scribble.network.model.CreateStory;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserStories {
    @POST(N.CREATE_USER_STORY)
    Call<BaseResponse> createStory(@Body CreateStory story);

    @GET(N.GET_ORIGINAL_STORIES)
    Call<List<Story>> getOriginalStories();
}