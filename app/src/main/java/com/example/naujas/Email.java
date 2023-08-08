package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.List;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import android.util.Log;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class Email extends AppCompatActivity {
    private static final String TAG = "EmailActivity";
    private EditText bodyEditText;
    private EditText emailEditText;
    private Button sendButton;
    private Spinner templatesSpinner;
    private List<Template> templates;
    private EditText toEditText;
    private Client selectedClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        templatesSpinner = findViewById(R.id.templatesSpinner);
        bodyEditText = findViewById(R.id.bodyEditText);
        sendButton = findViewById(R.id.sendButton);
        Spinner clientSpinner = findViewById(R.id.clientSpinner);
        emailEditText = findViewById(R.id.toEditText);

        templates = new ArrayList<>();
        TemplateSpinnerAdapter templateAdapter = new TemplateSpinnerAdapter(Email.this, templates);
        templatesSpinner.setAdapter(templateAdapter);
        DatabaseReference templatesRef = FirebaseDatabase.getInstance().getReference("templates");
        templatesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot templateSnapshot : snapshot.getChildren()) {
                    Template template = templateSnapshot.getValue(Template.class);
                    templates.add(template);
                }
                templateAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Error getting templates.", error.toException());
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
        templatesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Template selectedTemplate = (Template) parent.getItemAtPosition(position);
                String message = selectedTemplate.getBody();
                bodyEditText.setText(message);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        clientSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedClient = (Client) parent.getItemAtPosition(position);
                emailEditText.setText(selectedClient.getEmail());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        DatabaseReference clientsRef = FirebaseDatabase.getInstance().getReference("clients");
        clientsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Client> clients = new ArrayList<>();
                for (DataSnapshot clientSnapshot : snapshot.getChildren()) {
                    Client client = clientSnapshot.getValue(Client.class);
                    clients.add(client);
                }
                ArrayAdapter<Client> clientAdapter = new ArrayAdapter<>(Email.this, android.R.layout.simple_spinner_item, clients);
                clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                clientSpinner.setAdapter(clientAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    private void sendEmail() {
        String recipientList = selectedClient.getEmail();
        String[] recipients = recipientList.split(",");

        String subject = "Email Subject";
        String message = bodyEditText.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, recipients);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose an email client"));
    }
}




