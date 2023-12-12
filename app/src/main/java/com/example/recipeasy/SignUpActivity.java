package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipeasy.R.id;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private EditText passwordAgainText;
    private Button createAccountButton;
    private TextView signInButton;
    private TextView errorText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = findViewById(R.id.signup_email);
        passwordText = findViewById(R.id.signup_password);
        passwordAgainText = findViewById(R.id.signup_password_again);
        createAccountButton = findViewById(R.id.signup_button);
        signInButton = findViewById(R.id.signup_signin_clickable_text);
        errorText = findViewById(id.signup_error_text);

        signUpClicked(emailText.getText().toString(),passwordText.getText().toString(),passwordAgainText.getText().toString());
        signInClicked();
    }

    private void signUpClicked(String email, String password, String passwordAgain) {
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = true;
                String message ="";
                if(email.isEmpty() || email == null || password.isEmpty() || password == null){
                    isValid =  false;
                    message = "Email and password cannot be empty.";
                }
                if(password.length() < 8){
                    isValid = false;
                    message = "Password must contain at least 8 characters.";
                }
                if(!password.equals(passwordAgain)){
                    isValid = false;
                    message = "Passwords do not match.";
                }
                else{
                    isValid = true;
                    Controller.getUser().setEmail(email);
                    Controller.getUser().setPassword(password);
                }//
                if(isValid) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //If signed up successfully
                            if(task.isSuccessful()) {
                                Controller.createUserData(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                            }
                            else {
                                errorText.setText("Sign up failed.");
                            }
                        }
                    });
                }
                else {
                     errorText.setText(message);
                     errorText.setVisibility(View.VISIBLE);
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