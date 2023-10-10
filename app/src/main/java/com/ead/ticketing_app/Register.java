package com.ead.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.regex.Pattern;

public class Register extends AppCompatActivity {

    TextView loginLink;
    TextInputEditText name;
    TextInputEditText email;
    TextInputEditText nic;
    TextInputEditText password;
    Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginLink = (TextView) findViewById(R.id.loginLink);
        name = (TextInputEditText) findViewById(R.id.name);
        email = (TextInputEditText) findViewById(R.id.email);
        nic = (TextInputEditText) findViewById(R.id.nic);
        password = (TextInputEditText) findViewById(R.id.password);
        registerBtn = (Button) findViewById(R.id.RegisterButton);

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(isValidInput()){
                    MyDBHelper dbHelper = new MyDBHelper(Register.this);
                    int result = dbHelper.addTicket(
                            name.getText().toString(),
                            nic.getText().toString(),
                            email.getText().toString(),
                            password.getText().toString(),
                            true
                    );

                    if (result == 1) {
                        startActivity(new Intent(Register.this, MainActivity.class));
                    }
                }
            }
        });
    }

    private boolean isValidInput() {
        boolean isValid = true;

        if(name.getText().toString().isEmpty()){
            name.setError("Name is required");
            isValid = false;
        }

        if(password.getText().toString().isEmpty()){
            password.setError("Password is required");
            isValid = false;
        }

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!Pattern.matches(emailPattern, email.getText().toString())) {
            email.setError("Invalid email address");
            isValid = false;
        }

        if(nic.getText().toString().isEmpty()){
            nic.setError("NIC is required");
            isValid = false;
        }

        return isValid;
    }
}