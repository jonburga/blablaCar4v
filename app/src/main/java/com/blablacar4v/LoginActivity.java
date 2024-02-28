package com.blablacar4v;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;

import java.security.Provider;

public class LoginActivity extends AppCompatActivity {

    FirebaseAnalytics analytics;
    Bundle bundle;

    EditText emailEditText;
    EditText passwordEditText;
    TextView singUpButton;
    Button loginInButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginInButton = findViewById(R.id.loginInButton);
        singUpButton = findViewById(R.id.textViewSignUp);

        analytics = FirebaseAnalytics.getInstance(this);
        bundle = new Bundle();
        bundle.putString("message", "Integración de Firebase completada");
        analytics.logEvent("InitScreen", bundle);
        setUp();

        //SetUp

    }
    private void setUp(){
        singUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });

        loginInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailEditText.getText().toString().isEmpty() ||! passwordEditText.getText().toString().isEmpty()){
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString());
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        showHome(FirebaseAuth.getInstance().getCurrentUser().getEmail(), ProviderType.BASIC);
                    }
                }else{
                    showAlert();
                }
            }
        });
    }

    private void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Se ha producido un error autenticando el usuario");
        builder.setPositiveButton("Aceptar", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showHome(String email, ProviderType provider){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("provider", provider.name());
        startActivity(intent);
    }
}