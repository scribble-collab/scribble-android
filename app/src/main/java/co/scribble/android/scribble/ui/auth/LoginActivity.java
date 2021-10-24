package co.scribble.android.scribble.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import co.scribble.android.scribble.R;
import co.scribble.android.scribble.databinding.ActivityLoginBinding;
import co.scribble.android.scribble.helpers.Pref;
import co.scribble.android.scribble.helpers.Valid;
import co.scribble.android.scribble.models.Auth;
import co.scribble.android.scribble.ui.auth.viewmodel.AuthViewModel;
import co.scribble.android.scribble.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.msgResponse().observe(this, msg ->
                Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show());

        viewModel.loginResponse().observe(this, auth -> {
            if (auth != null) saveUser(auth);
        });

        binding.btnLogin.setOnClickListener(this);
        binding.linkSignup.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Pref.getAuthToken(this) != null)
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    private void saveUser(Auth auth) {
        Pref.authToken(this, auth.getAuthToken());
        Pref.saveUser(this, auth.getUserId());
        Snackbar.make(binding.getRoot(), "Login successful", Snackbar.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_login)
            submitLogin();

        if (id == R.id.link_signup)
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void submitLogin() {
        String username = null, password = null;

        if (binding.tedUsername.getText() != null)
            username = binding.tedUsername.getText().toString();

        if (binding.tedPassword.getText() != null)
            password = binding.tedPassword.getText().toString();

        if (Valid.text(binding.tilUsername, username)
                && Valid.password(binding.tilPassword, password)) {
            viewModel.loginUser(username, password);
        }
    }
}