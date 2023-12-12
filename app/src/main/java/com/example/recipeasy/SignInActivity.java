package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recipeasy.BackEnd.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button signInButton;
    private TextView errorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailText = findViewById(R.id.signin_email);
        passwordText = findViewById(R.id.signin_password);
        signInButton = findViewById(R.id.signin_button);
        errorText = findViewById(R.id.signin_error_text);

        signInClicked(emailText.getText().toString(), passwordText.getText().toString());
    }

    private void signInClicked(String email, String password) {
        errorText.setVisibility(View.GONE);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(email.isEmpty() || email == null || password.isEmpty() || password == null){
                            errorText.setText("Password and email cannot be blank");
                            errorText.setVisibility(View.VISIBLE);
                        }
                        if(task.isSuccessful()) {
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            //TODO: Update the current user
                        }
                        else{
                            errorText.setText("Login failed. Email or password is incorrect.");
                            errorText.setVisibility(View.VISIBLE);
                        }
                    }
                });
                //Initialize variables
                Controller controller = new Controller();
                controller.setUser(new User(email, password, FirebaseAuth.getInstance().getCurrentUser().getUid()));
            }
        });
    }
    }
