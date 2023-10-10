package com.ead.ticketing_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    //defining variables
    private static final String TAG = "MyApp";
    String dbNIC;
    String dbPASSWORD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find by ids
        TextInputEditText nic = (TextInputEditText) findViewById(R.id.usernameval);
        TextInputEditText password = (TextInputEditText) findViewById(R.id.passwordval);
        TextView registerLink = (TextView) findViewById(R.id.registerLink);

        Button loginButton = (Button) findViewById(R.id.loginbutton);

        //admin and admin
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper dbHelper = new MyDBHelper(MainActivity.this);
                Cursor cursor = dbHelper.searchForUser(nic.getText().toString());
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        dbNIC = cursor.getString(2);
                        dbPASSWORD = cursor.getString(4);

                        // Append the cursor details to the TextView
                        Log.d(TAG, "Result: " + dbNIC);

                    } while (cursor.moveToNext());
                    cursor.close();
                }

                //checking and matching password and nic
                if(nic.getText().toString().equals(dbNIC) && password.getText().toString().equals(dbPASSWORD)){
                    //correct
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Main_Menu.class));
                }else{
                    //incorrect
                    Toast.makeText(MainActivity.this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //navigation
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });
    }
}