package com.example.mobileapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity {
    private EditText inputEmail, inputPassword, inputName;
    private TextView txtWarnEmail, txtWarnPassword, txtWarnUsername;
    private Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signup);
        initViews();
    }

    private void initViews() {
        inputEmail = findViewById(R.id.editTextEmailAddress);
        inputPassword = findViewById(R.id.editTextPassword);
        inputName = findViewById(R.id.editTextUsername);
        buttonSignup = findViewById(R.id.buttonSignup);
        txtWarnEmail = findViewById(R.id.textWarnEmail);
        txtWarnPassword = findViewById(R.id.textWarnPassword);
        txtWarnUsername = findViewById(R.id.txtWarnUsername);
        txtWarnEmail.setVisibility(View.GONE);
        txtWarnPassword.setVisibility(View.GONE);
        txtWarnUsername.setVisibility(View.GONE);
    }

    private boolean validateData() {
        if (!isValidEmail(inputEmail.getText().toString())) {
            txtWarnEmail.setVisibility(View.VISIBLE);
            txtWarnEmail.setText("Email inválido");
            return false;
        }
        if (inputPassword.getText().toString().length() < 8) {
            txtWarnPassword.setVisibility(View.VISIBLE);
            txtWarnPassword.setText("Senha muito curta");
            return false;
        } else txtWarnPassword.setVisibility(View.GONE);
        if (inputName.getText().toString().equals("")) {
            txtWarnUsername.setVisibility(View.VISIBLE);
            txtWarnUsername.setText("");
            return false;
        }
       return true;
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX =
                "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
