package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.text.TextUtils;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddClient extends AppCompatActivity {

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        getSupportActionBar().hide();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Button addButton = findViewById(R.id.add_client_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user input for the client's name, email, and phone number
                EditText nameEditText = findViewById(R.id.client_name_edittext);
                String name = nameEditText.getText().toString();

                EditText emailEditText = findViewById(R.id.client_email_edittext);
                String email = emailEditText.getText().toString();

                EditText phoneEditText = findViewById(R.id.client_phone_edittext);
                String phone = phoneEditText.getText().toString();

                // Call the addClientToDatabase() method with the user input
                addClientToDatabase(name, email, phone);
            }
        });
    }

    private void addClientToDatabase(String name, String email, String phone) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phone)) {
            String id = databaseReference.child("clients").push().getKey();
            Client client = new Client(id, name, email, phone);
            databaseReference.child("clients").child(id).setValue(client);
            Toast.makeText(this, "Client added successfully", Toast.LENGTH_SHORT).show();
            finish(); // finish the activity and return to the previous activity
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
