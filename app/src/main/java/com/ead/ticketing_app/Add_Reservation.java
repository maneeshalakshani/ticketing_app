package com.ead.ticketing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Add_Reservation extends AppCompatActivity {
    private Button selectDateButton;
    private TextView selectedDateTextView;
    private Spinner spinner;

    private String selectedClass;
    private int year, month, day;

    private EditText noOfTickets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reservation);

        Button setReservationBtn = findViewById(R.id.setBtn);
        spinner = findViewById(R.id.trainClass);
        selectDateButton = findViewById(R.id.selectDateButton);
        selectedDateTextView = findViewById(R.id.selectedDateTextView);
        noOfTickets = findViewById(R.id.noOfTickets);

        // Define the list of items for the spinner
        String[] items = {"Class 1", "Class 2", "Class 3"};
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selected item here
                selectedClass = (String) parentView.getItemAtPosition(position);
                // You can perform actions based on the selected item
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here or add default behavior
            }
        });


        setReservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to launch the second activity
                Intent intent = new Intent(Add_Reservation.this, Summary.class);

                // Attach data to the Intent using putExtra
                intent.putExtra("date", selectedDateTextView.getText().toString());
                intent.putExtra("trainClass", selectedClass);
                intent.putExtra("noOfTickets", noOfTickets.getText().toString());

                // Start the second activity
                startActivity(intent);
            }
        });

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the current date as the default date in the date picker
                final Calendar calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                // Create a date picker dialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Add_Reservation.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                                // Update the selected date in the TextView
                                selectedDateTextView.setText("Selected Date: " + (selectedMonth + 1) + "/" + selectedDayOfMonth + "/" + selectedYear);
                            }
                        },
                        year,
                        month,
                        day
                );

                // Show the date picker dialog
                datePickerDialog.show();
            }
        });
    }
}