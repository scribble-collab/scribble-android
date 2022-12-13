package co.scribble.android.scribble.network.constant;

public class N {
    public static final String BASE_URL = "http://192.168.150.100:4000";

    // auth
    public static final String AUTH_USER_LOGIN = "/auth/login";
    public static final String AUTH_USER_SIGNUP = "/auth/signup";
    public static final String AUTH_LOGOUT = "/auth/logout";

    // stories
    public static final String CREATE_USER_STORY = "/story/create";
    public static final String GET_ORIGINAL_STORIES = "/story";
    public static final String
            STORY_SORT_LIKES_ASC = "STORY_SORT_LIKES_ASC",
            STORY_SORT_LIKES_DESC = "LIKES_DESC",
            STORY_SORT_COMMENTS_ASC = "COMMENTS_ASC",
            STORY_SORT_COMMENTS_DESC = "COMMENTS_DESC",
            STORY_SORT_CREATED_ASC = "CREATED_ASC",
            STORY_SORT_CREATED_DESC = "CREATED_DESC",
            STORY_SORT_UPDATED_ASC = "UPDATED_ASC",
            STORY_SORT_UPDATED_DESC = "UPDATED_DESC";

    public static final String
            STORY_TYPE_ALL = "ALL",
            STORY_TYPE_ORIGINAL = "ORIGINAL",
            STORY_TYPE_VERSION = "VERSION";
}
