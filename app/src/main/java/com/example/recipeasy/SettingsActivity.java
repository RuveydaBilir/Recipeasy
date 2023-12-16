package com.example.recipeasy;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

import com.example.recipeasy.BackEnd.Ingredient;
import com.example.recipeasy.BackEnd.ShoppingList;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private Button editProfileButton, suggestionsButton, changePasswordButton, logOutButton, resetDataButton;
    private AlertDialog alertDialog;
    AlertDialog.Builder builderDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.settings);
        
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.recipe) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.shopping_cart) {
                startActivity(new Intent(getApplicationContext(), ShoppingListActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.fridge) {
                startActivity(new Intent(getApplicationContext(), FridgeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.favorites) {
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.settings) {
                return true;
            }
            return false;
        });

        suggestionsButton = findViewById(R.id.suggestions_button);
        suggestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if (itemId == R.id.suggestions_button) {
                    startActivity(new Intent(getApplicationContext(), SuggestionsActivity.class));
                    finish();
                }
            }
        });

        changePasswordButton = findViewById(R.id.change_password_button);
        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if (itemId == R.id.change_password_button) {
                    startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
                    finish();
                }
            }
        });

        logOutButton = findViewById(R.id.log_out_button);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if (itemId == R.id.log_out_button) {
                    FirebaseAuth.getInstance().signOut();
                    Intent intent = new Intent(SettingsActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(SettingsActivity.this, "Logout Successful.", Toast.LENGTH_SHORT).show();                }
            }
        });


        editProfileButton = findViewById(R.id.edit_profile_button);
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if (itemId == R.id.edit_profile_button) {
                    startActivity(new Intent(getApplicationContext(), EditProfileActivity.class));
                    finish();
                }
            }
        });

        resetDataButton = findViewById(R.id.reset_data_button);
        resetDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if (itemId == R.id.reset_data_button) {
                    showAlertDialog(R.layout.activity_reset_data);
                }
            }

        });
    }

    private void showAlertDialog(int layout) {
        builderDialog = new AlertDialog.Builder(this);
        View layoutView = getLayoutInflater().inflate(layout,null);
        builderDialog.setView(layoutView);

        Button yesButton = layoutView.findViewById(R.id.delete_yes_button);
        Button noButton = layoutView.findViewById(R.id.delete_no_button);

        alertDialog = builderDialog.create();
        alertDialog.show();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: delete user data ya da useri kandiralim :))

                alertDialog.dismiss();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });



    }
}