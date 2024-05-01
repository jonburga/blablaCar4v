package com.blablacar4v;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

enum ProviderType {
    BASIC;
}
public class MainActivity extends AppCompatActivity {
    Bundle bundle;
    TextView tx;
    Button logoutButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        bundle = getIntent().getExtras();
        tx = findViewById(R.id.textView5);
        logoutButton = findViewById(R.id.logoutButton);


    }

}