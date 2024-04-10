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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        tx = findViewById(R.id.textView5);
        logoutButton = findViewById(R.id.logoutButton);
        String email = bundle.getString("email");
        String provider = bundle.getString("provider");
        setUp(email, ProviderType.valueOf(provider));
    }
    private void setUp(String email, ProviderType provider){
        tx.setText(email);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            onBackPressed();
                });
    }

}