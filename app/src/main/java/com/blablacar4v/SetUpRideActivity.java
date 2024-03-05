package com.blablacar4v;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.blablacar4v.models.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class SetUpRideActivity extends AppCompatActivity {
    FloatingActionButton homeButton;
    TimePickerDialog timePickerDialog;
    EditText editArrivalTime;
    EditText editDepartureTime;
    EditText editDate;
    EditText editDeperaturePlace;
    EditText editArrivalPlace;
    EditText editSeats;

    private FirebaseFirestore db;
    Button setUpButton;
    Bundle bundle;
    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_ride);
        homeButton = findViewById(R.id.floatingHome);
        editArrivalTime = findViewById(R.id.editArrivalTime);
        editDepartureTime = findViewById(R.id.editDepartureTime);
        setUpButton = findViewById(R.id.buttonAdd);
        editDate = findViewById(R.id.editDate);
        editDeperaturePlace = findViewById(R.id.editDeparturePlace);
        editArrivalPlace = findViewById(R.id.editArrivalPlace);
        editSeats = findViewById(R.id.editSeats);
        bundle = getIntent().getExtras();
        email = bundle.getString("email");
        db = FirebaseFirestore.getInstance();
        editArrivalTime.setOnClickListener(v -> {
            timePickerDialog = new TimePickerDialog(SetUpRideActivity.this, (view, hourOfDay, minute) -> {
                editArrivalTime.setText(hourOfDay + ":" + minute);
            }, 0, 0, true);
            timePickerDialog.show();
        });
        editDepartureTime.setOnClickListener(v -> {
            timePickerDialog = new TimePickerDialog(SetUpRideActivity.this, (view, hourOfDay, minute) -> {
                editDepartureTime.setText(hourOfDay + ":" + minute);
            }, 0, 0, true);
            timePickerDialog.show();
        });


        homeButton.setOnClickListener(v -> onBackPressed());
        setUp();
    }

    private void setUp() {
        setUpButton.setOnClickListener(v -> {
            if (!editArrivalTime.getText().toString().isEmpty() || !editDepartureTime.getText().toString().isEmpty() || !editDate.getText().toString().isEmpty() || !editDeperaturePlace.getText().toString().isEmpty() || !editArrivalPlace.getText().toString().isEmpty() || !editSeats.getText().toString().isEmpty()) {
                db.collection("travels").document().set(new Travel(
                        editArrivalTime.getText().toString(),
                        editArrivalPlace.getText().toString(),
                        editDate.getText().toString(),
                        editDepartureTime.getText().toString(),
                        editDeperaturePlace.getText().toString(),
                        "",
                        Integer.parseInt(editSeats.getText().toString()),
                        email
                ));
            }
        });
    }

}