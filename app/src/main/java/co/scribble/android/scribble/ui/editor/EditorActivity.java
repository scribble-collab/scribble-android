package co.scribble.android.scribble.ui.editor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.databinding.ActivityEditorBinding;
import io.github.mthli.knife.KnifeText;

public class EditorActivity extends AppCompatActivity {
    private ActivityEditorBinding binding;
    private KnifeText knife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String initialData = getIntent().getStringExtra(C.EDITOR_RESULT_KEY);

        knife = binding.knife;
        if ((initialData != null)) {
            knife.fromHtml(initialData);
        } else {
            knife.setHint("Start writing here...");
        }
        knife.setSelection(knife.getEditableText().length());

        setupTools();
    }

    private void setupTools() {
        binding.bold.setOnClickListener(v -> knife.bold(!knife.contains(KnifeText.FORMAT_BOLD)));
        binding.italic.setOnClickListener(v -> knife.italic(!knife.contains(KnifeText.FORMAT_ITALIC)));
        binding.underline.setOnClickListener(v -> knife.underline(!knife.contains(KnifeText.FORMAT_UNDERLINED)));
        binding.strikethrough.setOnClickListener(v -> knife.strikethrough(!knife.contains(KnifeText.FORMAT_STRIKETHROUGH)));
        binding.clear.setOnClickListener(v -> knife.clearFormats());
        binding.redo.setOnClickListener(v -> knife.redo());
        binding.undo.setOnClickListener(v -> knife.undo());

        binding.done.setOnClickListener(v -> {
            setResult(RESULT_OK, new Intent().putExtra(C.EDITOR_RESULT_KEY, knife.toHtml()));
            finish();
        });
    }
}