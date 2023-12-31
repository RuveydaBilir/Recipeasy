package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipeasy.BackEnd.User;
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
        //Initialize controller
        Controller controller = new Controller();
        //Go to the main page
        if(Controller.isUserSignedIn()) {
            User user = new User();
            user.setUserID(FirebaseAuth.getInstance().getCurrentUser().getUid());
            Controller.setUser(user);
            Controller.setUserData();
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = findViewById(R.id.signup_email);
        passwordText = findViewById(R.id.signup_password);
        passwordAgainText = findViewById(R.id.signup_password_again);
        createAccountButton = findViewById(R.id.signup_button);
        signInButton = findViewById(R.id.signup_signin_clickable_text);
        errorText = findViewById(id.signup_error_text);

        signUpClicked();
        signInClicked();
    }

    private void signUpClicked() {
        errorText.setVisibility(View.GONE);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            String email;
            String password;
            String passwordAgain;
            @Override
            public void onClick(View v) {
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                passwordAgain = passwordAgainText.getText().toString();

                boolean isValid = true;
                String message ="";
                if(email.isEmpty() || password.isEmpty() ||  passwordAgain.isEmpty() || email == null || password == null || passwordAgain == null){
                    isValid =  false;
                    message = "Email and password cannot be blank.";
                }
                else if(!password.equals(passwordAgain)){
                    isValid = false;
                    message = "Passwords do not match.";
                }
                else if(password.length() < 8){
                    isValid = false;
                    message = "Password must contain at least 8 characters.";
                }

                if(isValid) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //If signed up successfully
                            if(task.isSuccessful()) {
                                //Initialize user data in database
                                Controller.createUserData(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                //Set the user
                                Controller.setUser(new User(email, password, FirebaseAuth.getInstance().getCurrentUser().getUid()));
                                //Set the user data
                                Controller.setUserData();
                                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                errorText.setText("Sign up failed.");
                                errorText.setVisibility(View.VISIBLE);
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