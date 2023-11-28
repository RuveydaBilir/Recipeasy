package com.example.recipeasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button signInButton;
    private FirebaseAuth myAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        emailText = findViewById(R.id.signin_email);
        passwordText = findViewById(R.id.signin_password);
        signInButton = findViewById(R.id.signin_button);
        myAuth = FirebaseAuth.getInstance();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid;
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();
                isValid = true; //TODO: Set the signing in conditions, also the email and the password cannot be blank
                if(isValid) {
                // Sign in the user
                    myAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //If signed in successfully
                            if(task.isSuccessful()) {
                            //TODO: Update the UI accordingly
                            //TODO: Go to the next page
                            }
                            else {
                                //TODO: Display a sign in failure message
                            }
                        }
                    });
                }
                else {
                    //TODO: Show the error message and maybe validation conditions
                }
            }
        });
    }
}