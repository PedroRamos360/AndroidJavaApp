package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        handleBarNavigation();
        handleImageClick();
        handleButtonClick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            ImageView profileImage = findViewById(R.id.profileImage);
            profileImage.setImageURI(data.getData());
        }
    }

    private void startImageSelector() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), 1);
    }

    private void handleImageClick() {
        ImageView profileImage = findViewById(R.id.profileImage);
        profileImage.setOnClickListener(v -> startImageSelector());
    }

    private void handleButtonClick() {
        Button button = findViewById(R.id.saveProfileButton);
        button.setOnClickListener(b -> Toast.makeText(Profile.this, "Perfil salvo com sucesso!", Toast.LENGTH_SHORT).show());

    }

    private void handleBarNavigation() {
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.getMenu().findItem(R.id.navigation_profile).setChecked(true);
        navbar.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.navigation_matches) {
                Intent intent = new Intent(Profile.this, Messages.class);
                startActivity(intent);
            } else if (menuItem.getItemId() == R.id.navigation_search) {
                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
            return false;
        });
    }
}
