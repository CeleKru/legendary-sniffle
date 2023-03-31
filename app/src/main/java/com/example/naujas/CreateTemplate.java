package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.os.Bundle;
import android.text.TextUtils;
//import com.example.naujas.Template;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CreateTemplate extends AppCompatActivity {

    private EditText subjectEditText;
    private EditText bodyEditText;
    private DatabaseReference templatesRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_template);

        getSupportActionBar().hide();

        // Initialize Firebase database reference
        templatesRef = FirebaseDatabase.getInstance().getReference().child("templates");

        // Find the subject and body EditText fields
        subjectEditText = findViewById(R.id.subjectEditText);
        bodyEditText = findViewById(R.id.bodyEditText);

        // Set up button click listener to save template to database
        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTemplate();
            }
        });
    }

    private void saveTemplate() {
        // Get the subject and body text from the EditText fields
        String subject = subjectEditText.getText().toString().trim();
        String body = bodyEditText.getText().toString().trim();

        // Check if subject and body are not empty
        if (TextUtils.isEmpty(subject)) {
            subjectEditText.setError("Subject is required");
            subjectEditText.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(body)) {
            bodyEditText.setError("Body is required");
            bodyEditText.requestFocus();
            return;
        }

        // Generate a unique key for the new template
        String templateId = templatesRef.push().getKey();

        // Create a new template object with the given subject and body text
        Template template = new Template(templateId, subject, body);

        // Save the new template to the database under the unique key
        templatesRef.child(templateId).setValue(template);

        // Show a success message and finish the activity
        Toast.makeText(this, "Template saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
