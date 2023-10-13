//package com.ead.ticketing_app;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.ead.ticketing_app.Adapter.ReservationAdapter;
//
//import java.util.ArrayList;
//
//public class Reservation extends AppCompatActivity {
//
//    MyDBHelper myDBHelper;
//    ArrayList<String> item_id, name, price_per_day, no_of_days, status;
//    ReservationAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reservation);
//
//        RecyclerView recycleView = findViewById(R.id.recycleView);
////        Button nextBtn = findViewById(R.id.nextBtn);
//
//        CardView card1 = findViewById(R.id.card1);
//        CardView card2 = findViewById(R.id.card2);
//        CardView card3 = findViewById(R.id.card3);
//
//        card1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(Reservation.this, Add_Reservation.class));
//            }
//        });
//
//        myDBHelper = new MyDBHelper(Reservation.this);
//        item_id = new ArrayList<>();
//        name = new ArrayList<>();
//        price_per_day = new ArrayList<>();
//        no_of_days = new ArrayList<>();
//        status = new ArrayList<>();
//
//        StoreDataInAnArray();
//
////        adapter = new ReservationAdapter(Reservation.this, item_id, name, price_per_day, no_of_days, status);
////        recycleView.setAdapter(adapter);
////        recycleView.setLayoutManager(new LinearLayoutManager(Reservation.this));
//    }
//
//    //to display data
//    void StoreDataInAnArray() {
//        Cursor cursor = myDBHelper.readAllData();
//        if(cursor.getCount() == 0){
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
//        }else{
//            while (cursor.moveToNext()) {
//                item_id.add(cursor.getString(0));
//                name.add(cursor.getString(1));
//                price_per_day.add(cursor.getString(2));
//                no_of_days.add(cursor.getString(3));
//                status.add(cursor.getString(4));
//            }
//        }
//    }
//}