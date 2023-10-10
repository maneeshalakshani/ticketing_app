package com.ead.ticketing_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class My_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ImageView editBtn = (ImageView) findViewById(R.id.editBtn);
        TextView deactivateBtn = (TextView) findViewById(R.id.deactivateBtn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(My_Profile.this, Edit_Profile.class));
            }
        });

        deactivateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create and configure the alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(My_Profile.this);
                builder.setTitle("Deactivate Your Account")
                        .setMessage("Once you clicked OK, your account will deactivated. Once its deactivated you cannot reactivate account. If you wants to reactivate, contact the back-officer")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the OK button click (if needed)
                                Toast.makeText(My_Profile.this, "Account Deactivate Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(My_Profile.this, MainActivity.class));
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Handle the Cancel button click (if needed)
                                Toast.makeText(My_Profile.this, "Deactivation Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });

                // Create and show the alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}