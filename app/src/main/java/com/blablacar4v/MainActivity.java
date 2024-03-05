package com.blablacar4v;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blablacar4v.models.Travel;
import com.blablacar4v.models.User;
import com.blablacar4v.recycler.UserAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

enum ProviderType {
    BASIC;
}
public class MainActivity extends AppCompatActivity {
    Bundle bundle;
    TextView tx;
    Button logoutButton;
    TabLayout addRide;
    RecyclerView recyclerView;
    UserAdapter adapter;
    List<User> users;
    List<Travel> travels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bundle = getIntent().getExtras();
        tx = findViewById(R.id.textView5);
        logoutButton = findViewById(R.id.logoutButton);
        addRide = findViewById(R.id.tabLayout);
        String email = bundle.getString("email");
        String provider = bundle.getString("provider");
        recyclerView = findViewById(R.id.recyclerView);
        users = Program.management.getUsers();
        travels = Program.management.getTravels();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new UserAdapter(travels, travel -> {
            Intent intent = new Intent(MainActivity.this, RideActivity.class);
            intent.putExtra("travel", (CharSequence) travel);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        setUp(email, ProviderType.valueOf(provider));
        addRide.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                }else if (tab.getPosition() == 1){
                    Intent intent = new Intent(MainActivity.this, SetUpRideActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }/*else if (tab.getPosition() == 2){
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                }*/
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    private void setUp(String email, ProviderType provider){
        tx.setText(email);
        logoutButton.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            onBackPressed();
                });
    }



}