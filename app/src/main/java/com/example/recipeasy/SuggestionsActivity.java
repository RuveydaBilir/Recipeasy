package com.example.recipeasy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.FirebaseDatabase;

public class SuggestionsActivity extends AppCompatActivity {

    Button sendButton;

    ImageButton backButton;
    EditText suggestionBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

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

        sendButton = findViewById(R.id.suggestions_button);
        suggestionBox = findViewById(R.id.suggestions_box);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();

                if(itemId == R.id.suggestions_button){
                    FirebaseDatabase.getInstance().getReference().child("Suggestions").push().child(Controller.getUser().getUserID()).setValue(suggestionBox.getText().toString());
                    //TODO: Show the message that the suggestion is send, belki aşağıdaki
                    Toast.makeText(SuggestionsActivity.this, "Your suggestion is send!", Toast.LENGTH_LONG).show();

                }
            }
        });

        backButton = findViewById(R.id.suggestions_return_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemId = v.getId();
                if(itemId == R.id.suggestions_return_button){
                    startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                    finish();
                }
            }
        });

    }
}