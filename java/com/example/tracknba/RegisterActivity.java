package com.example.tracknba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    private EditText email,password,username;
    private Button btnRegister;
    private TextView textLogin;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        btnRegister = findViewById(R.id.register);
        textLogin = findViewById(R.id.text_login);
        username = findViewById(R.id.register_user);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void Register() {
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String usr = username.getText().toString().trim();
        if(user.isEmpty()) {
            email.setError("Email cannot be empty");
        }
        if(user.contains(".edu")) {
            email.setError("Please do not use a school email");
        }
        if(pass.isEmpty()) {
            password.setError("Password cannot be empty");
        }
        if(pass.equals(usr)) {
            password.setError("Username and Password cannot be the same");
            username.setError("Username and Password cannot be the same");
        }
        if(usr.isEmpty()) {
            username.setError("Username cannot be empty");
        }
        if(usr.length() > 20) {
            username.setError("Your username cannot be over 20 characters");
        }
        else if (!(pass.equals(usr)) && usr.length() <= 20 && !(user.contains(".edu"))) {
            mAuth.createUserWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        String teamn = "Go to the 'Search for team' and/or 'Search for player pages to add your favorite teams and players to your homepage!";
                        Toast.makeText(RegisterActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        userID = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = fStore.collection(userID).document("Favorites List");
                        Map<String,Object> player = new HashMap<>();
                        player.put("favoritePlayers", teamn);
                        player.put("display", "yes");
                        documentReference.set(player).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSuccess: profile is created for "+userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "onSuccess: "+e.toString());
                            }
                        });

                        String name = usr;
                        DocumentReference documentReference1 = fStore.collection("Username").document(userID);
                        Map<String,Object> usern = new HashMap<>();
                        usern.put("User", name);
                        documentReference1.set(usern).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSuccess: profile is created for "+userID);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.d("TAG", "onSuccess: "+e.toString());
                            }
                        });;

                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("remember", "true");
                        editor.apply();
                        Toast.makeText(RegisterActivity.this,"Welcome",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}