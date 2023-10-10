package com.ead.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Edit_Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Button edit_Profile_btn = (Button) findViewById(R.id.edit_profile_btn);

        edit_Profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Edit_Profile.this, "Edit Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Edit_Profile.this, My_Profile.class));
            }
        });
    }
}