package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {
    private RecyclerView contactsRecView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.messages);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.messages), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.getMenu().findItem(R.id.navigation_profile).setChecked(true);
        navbar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.navigation_search) {
                    Intent intent = new Intent(Messages.this, MainActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        hideSystemUI();
        contactsRecView = findViewById(R.id.contactsRecView);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Margot Robbie", "Oi, tudo bem?", "@mipmap/woman1"));
        contacts.add(new Contact("Margot Robbie", "Oi, tudo bem?", "@mipmap/woman1"));
        contacts.add(new Contact("Margot Robbie", "Oi, tudo bem?", "@mipmap/woman1"));
        ContactViewAdapter adapter = new ContactViewAdapter();
        adapter.setContacts(contacts);
        contactsRecView.setAdapter(adapter);
        // the following line crashes the application
        //contactsRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }
}
