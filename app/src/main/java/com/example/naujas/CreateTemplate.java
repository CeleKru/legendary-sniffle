package com.example.naujas;
import androidx.appcompat.app.AppCompatActivity;

//import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
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
        templatesRef = FirebaseDatabase.getInstance().getReference().child("templates");
        subjectEditText = findViewById(R.id.subjectEditText);
        bodyEditText = findViewById(R.id.bodyEditText);
        Button saveButton = findViewById(R.id.saveButton);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTemplate();
            }
        });
    }
    private void saveTemplate() {
        String subject = subjectEditText.getText().toString().trim();
        String body = bodyEditText.getText().toString().trim();
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
        String templateId = templatesRef.push().getKey();
        Template template = new Template(templateId, subject, body);
        templatesRef.child(templateId).setValue(template);
        Toast.makeText(this, "Template saved successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
