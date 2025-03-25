package com.example.tracknba;


import android.os.Bundle;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.AppBarConfiguration;
//import androidx.navigation.ui.NavigationUI;


import com.example.tracknba.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {
    Button btnupdte;
    EditText newusrnm;
    FirebaseFirestore fStore;
    TextView username;
    String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        username = findViewById(R.id.current_username);
        btnupdte = findViewById(R.id.button_update);
        newusrnm = findViewById(R.id.new_username);

        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Username").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                username.setText(value.getString("User"));
            }
        });
        btnupdte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = newusrnm.getText().toString();
                if (usr.isEmpty()) {
                    newusrnm.setError("New username cannot be empty!");
                }
                else {
                    DocumentReference documentReference1 = fStore.collection("Username").document(userID);
                    Map<String,Object> usern = new HashMap<>();
                    usern.put("User", usr);
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
                    });
                }
            }
        });
    }
}