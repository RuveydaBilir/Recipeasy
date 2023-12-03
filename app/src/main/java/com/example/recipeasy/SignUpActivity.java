package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private EditText passwordAgainText;
    private Button createAccountButton;
    private TextView signInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = findViewById(R.id.signup_email);
        passwordText = findViewById(R.id.signup_password);
        passwordAgainText = findViewById(R.id.signup_password_again);
        createAccountButton = findViewById(R.id.signup_button);
        signInButton = findViewById(R.id.signup_signin_clickable_text);

        signUpClicked(emailText.getText().toString(), passwordText.getText().toString(), passwordAgainText.getText().toString());
        signInClicked();
    }

    private void signUpClicked(String email, String password, String passwordAgain) {
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true; //TODO: Set the signing up conditions, also the email and the password cannot be blank
                if(isValid) {
                    Controller.signUp(email, password);
                }
                else {
                    //TODO: Show the error message and maybe validation conditions
                }
            }
        });
    }

    private void signInClicked() {
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
            }
        });
    }
}