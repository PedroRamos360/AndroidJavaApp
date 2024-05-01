package com.example.mobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
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
        handleBarNavigation();
        contactsRecView = findViewById(R.id.contactsRecView);
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Emily Anderson", "Oi, tudo bem?", R.mipmap.woman1));
        contacts.add(new Contact("Sophia Martinez", "Oi, tudo bem?", R.mipmap.woman2));
        contacts.add(new Contact("Isabella Nguyen", "Oi, tudo bem?", R.mipmap.woman4));
        ContactViewAdapter adapter = new ContactViewAdapter();
        adapter.setContacts(contacts);
        contactsRecView.setAdapter(adapter);
        contactsRecView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void handleBarNavigation() {
        BottomNavigationView navbar = findViewById(R.id.bottom_navigation);
        navbar.getMenu().findItem(R.id.navigation_matches).setChecked(true);
        navbar.setOnItemSelectedListener(menuItem -> {
            if (menuItem.getItemId() == R.id.navigation_search) {
                Intent intent = new Intent(Messages.this, MainActivity.class);
                startActivity(intent);
            } else if (menuItem.getItemId() == R.id.navigation_profile) {
                Intent intent = new Intent(Messages.this, Profile.class);
                startActivity(intent);
            }
            return false;
        });
    }
}
