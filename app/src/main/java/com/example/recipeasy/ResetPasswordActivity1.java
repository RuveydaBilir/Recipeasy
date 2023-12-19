package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity1 extends AppCompatActivity {

    Button sendMailButton;
    EditText emailText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password1);

        sendMailButton = findViewById(R.id.resetpassword1_button);
        emailText = findViewById(R.id.resetpassword1_email);
        sendMailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(ResetPasswordActivity1.this, "Please enter your email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                FirebaseAuth.getInstance().sendPasswordResetEmail(email);
                Toast.makeText(ResetPasswordActivity1.this, "Password reset email has been send!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ResetPasswordActivity1.this, SignInActivity.class));
                finish();
            }
        });
    }
}