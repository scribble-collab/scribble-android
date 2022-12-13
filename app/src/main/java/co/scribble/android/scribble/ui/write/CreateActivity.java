package co.scribble.android.scribble.ui.write;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.databinding.ActivityCreateBinding;
import co.scribble.android.scribble.helpers.Valid;
import co.scribble.android.scribble.network.model.CreateStory;
import co.scribble.android.scribble.ui.editor.EditorActivity;
import co.scribble.android.scribble.ui.write.viewmodel.CreateStoryViewModel;

public class CreateActivity extends AppCompatActivity {

    private static final int EDITOR_REQ = 101;
    private ActivityCreateBinding binding;
    private CreateStoryViewModel viewModel;
    private String body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(CreateStoryViewModel.class);
        viewModel.createResponse().observe(this, res ->
                Snackbar.make(binding.getRoot(), res, Snackbar.LENGTH_SHORT).show());

        binding.btnStoryCreate.setOnClickListener(v ->
                postUserStory());

        binding.tedStory.setOnFocusChangeListener((view, b) -> {
            if (b) {
                startActivityForResult(new Intent(CreateActivity.this, EditorActivity.class)
                        .putExtra(C.EDITOR_RESULT_KEY, body), EDITOR_REQ);
            }
        });

        binding.tedStory.setOnClickListener((view) ->
                startActivityForResult(new Intent(CreateActivity.this, EditorActivity.class)
                        .putExtra(C.EDITOR_RESULT_KEY, body), EDITOR_REQ));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDITOR_REQ && resultCode == RESULT_OK) {
            if (data == null) return;

            body = data.getStringExtra(C.EDITOR_RESULT_KEY);
            binding.tedStory.setText(Html.fromHtml(body));
        }
    }

    private void postUserStory() {
        String title = null, desc = null;

        if (binding.tedStoryTitle.getText() != null)
            title = binding.tedStoryTitle.getText().toString();

        if (binding.tedStoryDesc.getText() != null)
            desc = binding.tedStoryDesc.getText().toString();

        if (body == null)
            Snackbar.make(binding.getRoot(), "Story cannot be empty.", Snackbar.LENGTH_SHORT).show();

        List<String> tags = binding.tedStoryTags.getTags();

        if (Valid.text(binding.tilStoryTitle, title) &&
                Valid.text(binding.tilStoryDesc, desc) &&
                Valid.text(binding.tilStory, body)
        ) {

            CreateStory story = new CreateStory();
            story.setTitle(title);
            story.setDescription(desc);
            story.setTags(tags);
            story.setBody(body);

            viewModel.createStory(story);
        }
    }
}