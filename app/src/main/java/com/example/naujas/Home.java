package com.example.naujas;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    private Button moreButton;
    private Button createEmailButton;
    private Button createTemplateButton;
    private Button templateListButton;
    private Button addClientButton;
    private Button clientListButton;
    private FirebaseAuth FirebaseAuth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0);


        createEmailButton = findViewById(R.id.create_email_button);
        //moreButton = findViewById(R.id.more_button);
        createTemplateButton = findViewById(R.id.create_template_button);
        templateListButton = findViewById(R.id.template_list_button);
        addClientButton = findViewById(R.id.add_client_button);
        clientListButton = findViewById(R.id.client_list_button);

        PopupMenu popupMenu = new PopupMenu(this, moreButton);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        //logout
                        return true;
                    default:
                        return false;
                }
            }
        });
        createEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, Email.class);
                startActivity(intent);
            }
        });

        createTemplateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, CreateTemplate.class);
                startActivity(intent);
            }
        });

        templateListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, TemplateList.class);
                startActivity(intent);
            }
        });
        addClientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, AddClient.class);
                startActivity(intent);
            }
        });
        clientListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, ClientList.class);
                startActivity(intent);
            }
        });
    }
}