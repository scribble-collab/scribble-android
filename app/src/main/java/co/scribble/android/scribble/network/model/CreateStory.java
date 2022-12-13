package co.scribble.android.scribble.network.model;

import java.util.List;

public class CreateStory {
    private String ref;
    private String title;
    private String description;
    private String body;
    private String cover;
    private List<String> tags;

    public CreateStory() {
    }

    public CreateStory(String ref, String title, String description, String body, String cover, List<String> tags) {
        this.ref = ref;
        this.title = title;
        this.description = description;
        this.body = body;
        this.cover = cover;
        this.tags = tags;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}


