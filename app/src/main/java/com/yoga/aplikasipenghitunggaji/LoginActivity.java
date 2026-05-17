package com.yoga.aplikasipenghitunggaji;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);

        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {

            String username =
                    etUsername.getText().toString();

            String password =
                    etPassword.getText().toString();if(username.equals("admin")
                    && password.equals("12345")) {

                Toast.makeText(
                        this,
                        "Login Berhasil",
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(
                        LoginActivity.this,
                        MainActivity.class);

                startActivity(intent);

                finish();} else {

                Toast.makeText(
                        this,
                        "Username atau Password Salah",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}