package co.scribble.android.scribble.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import co.scribble.android.scribble.R;
import co.scribble.android.scribble.databinding.ActivityRegisterBinding;
import co.scribble.android.scribble.helpers.Pref;
import co.scribble.android.scribble.helpers.Valid;
import co.scribble.android.scribble.models.Auth;
import co.scribble.android.scribble.ui.auth.viewmodel.AuthViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        viewModel.registerResponse().observe(this, auth -> {
            if (auth != null) saveUser(auth);
        });

        viewModel.msgResponse().observe(this, msg ->
                Snackbar.make(binding.getRoot(), msg, Snackbar.LENGTH_SHORT).show());

        binding.btnSignup.setOnClickListener(this);
        binding.linkLogin.setOnClickListener(this);
    }

    private void saveUser(Auth auth) {
        Pref.authToken(this, auth.getAuthToken());
        Pref.saveUser(this, auth.getUserId());
        Snackbar.make(binding.getRoot(), "Registration successful", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btn_signup)
            submitReg();

        if (id == R.id.link_login)
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    private void submitReg() {
        String username = null, password = null;

        if (binding.tedUsername.getText() != null)
            username = binding.tedUsername.getText().toString();

        if (binding.tedPassword.getText() != null)
            password = binding.tedPassword.getText().toString();

        if (Valid.text(binding.tilUsername, username)
                && Valid.password(binding.tilPassword, password)) {
            viewModel.registerUser(username, password);
        }
    }
}