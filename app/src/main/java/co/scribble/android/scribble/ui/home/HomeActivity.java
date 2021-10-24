package co.scribble.android.scribble.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import co.scribble.android.scribble.databinding.ActivityHomeBinding;
import co.scribble.android.scribble.ui.write.CreateActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabCreateStory.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, CreateActivity.class)));
    }
}