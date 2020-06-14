package com.example.handinv1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.handinv1.Authentication.Login;
import com.example.handinv1.Quiz.Quiz;
import com.example.handinv1.View.ListFragment;
import com.example.handinv1.View.PokeDexFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ListFragment()).commit();
        navigationView.setCheckedItem(R.id.nav_list);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_list:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ListFragment()).commit();
                break;
            case R.id.nav_pokedex:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new PokeDexFragment()).commit();

                break;
            case R.id.nav_share:
                String action = Intent.ACTION_VIEW;
                Uri uri = Uri.parse("https://cdn.discordapp.com/attachments/588288675163537423/721426379803066408/unknown.png");

                Intent intent = new Intent(action, uri);
                startActivity(intent);

                break;
            case R.id.nav_quiz:
                startActivity(new Intent(getApplicationContext(), Quiz.class));
                break;

            case R.id.logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}