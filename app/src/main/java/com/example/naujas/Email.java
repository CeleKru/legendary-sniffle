package com.example.naujas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Email extends AppCompatActivity {
    private EditText bodyEditText;
    private Button sendButton;
    private Spinner templatesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        getSupportActionBar().hide();

        templatesSpinner = findViewById(R.id.templatesSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.email_templates, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        templatesSpinner.setAdapter(adapter);

        bodyEditText = findViewById(R.id.bodyEditText);
        templatesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTemplate = parent.getItemAtPosition(position).toString();
                String message = "";
                switch (selectedTemplate) {
                    case "Template 1":
                        message = getString(R.string.template1);
                        break;
                    case "Template 2":
                        message = getString(R.string.template2);
                        break;
                    case "Welcome email":
                        message = getString(R.string.template3);
                        break;
                    case "Confirmation email":
                        message = getString(R.string.template4);
                        break;
                    case "Newsletter email":
                        message = getString(R.string.template5);
                        break;
                    case "Feedback request email":
                        message = getString(R.string.template6);
                        break;
                    case "Template 7":
                        message = getString(R.string.template7);
                        break;
                }
                bodyEditText.setText(message);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String recipientList = "recipient@example.com";
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
