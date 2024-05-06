package com.blablacar4v;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.blablacar4v.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SignUpActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    EditText emailEditText;
    EditText passwordEditText;
    EditText directionEditText;
    EditText phoneEditText;
    EditText nameEditText;
    Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        directionEditText = findViewById(R.id.directionEdit);
        phoneEditText = findViewById(R.id.telephonEditText);
        nameEditText = findViewById(R.id.nameEditText);
        db = FirebaseFirestore.getInstance();
        setUp();
    }

    private void setUp() {
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!emailEditText.getText().toString().isEmpty() || !passwordEditText.getText().toString().isEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                            .addOnSuccessListener(authResult -> {
                                db.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(new User(
                                        emailEditText.getText().toString(),
                                        passwordEditText.getText().toString(),
                                        directionEditText.getText().toString(),
                                        phoneEditText.getText().toString(),
                                        nameEditText.getText().toString()
                                ));
                                showHome(FirebaseAuth.getInstance().getCurrentUser().getEmail(), ProviderType.BASIC);
                            })
                            .addOnFailureListener(e -> {
                                // Manejar fallo en la creación del usuario
                                Toast.makeText(SignUpActivity.this, "Error en el registro: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
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
    private boolean validateDomain(String validEmail) {
        String[] validDomains = {"gmail.com", "hotmail.com"};

        String[] emailParts = validEmail.split("@");
        if (emailParts.length == 2) {
            String domain = emailParts[1].toLowerCase();

            for (String validDomain : validDomains) {
                if (domain.equals(validDomain)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean validateEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailPattern);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }
}