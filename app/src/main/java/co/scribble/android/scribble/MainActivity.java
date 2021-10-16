package co.scribble.android.scribble;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import co.scribble.android.scribble.databinding.ActivityMainBinding;
import co.scribble.android.scribble.helpers.Pref;
import co.scribble.android.scribble.ui.auth.LoginActivity;
import co.scribble.android.scribble.ui.auth.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.flow.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, RegisterActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        String token = Pref.getAuthToken(this);
        if (token != null) startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}