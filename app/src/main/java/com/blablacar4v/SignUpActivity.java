package com.blablacar4v;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;



public class SignUpActivity extends AppCompatActivity {

    EditText emailEditText;
    EditText passwordEditText;
    Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        setUp();
    }

    private  void setUp(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailEditText.getText().toString().isEmpty() || !passwordEditText.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString());
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        showHome(FirebaseAuth.getInstance().getCurrentUser().getEmail(), ProviderType.BASIC);
                    }
                } else {
                    showAlert();
                }
            }
        });
    }



    private void showHome(String name, ProviderType provider){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("provider", provider.toString());
        startActivity(intent);
    }

    private void showAlert(){
        Toast.makeText(this, "Se ha producido un error", Toast.LENGTH_SHORT).show();
    }
}