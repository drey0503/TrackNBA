package com.example.tracknba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class TestLink extends AppCompatActivity {
    EditText newUsername;
    TextView currentUsername;
    Button update;
    FirebaseFirestore fStore;
    String userID;
    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_link);

        newUsername = findViewById(R.id.newUser);
        update = findViewById(R.id.btnupdate);
        currentUsername = findViewById(R.id.current_username);
        fStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = fStore.collection("Username").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                currentUsername.setText(value.getString("User"));
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usr = newUsername.getText().toString();
                userID = mAuth.getCurrentUser().getUid();
                if (usr.isEmpty()) {
                    newUsername.setError("New username cannot be empty!");
                }
                if (usr.length() > 20) {
                    newUsername.setError("Your username cannot be over 20 characters");
                }
                else if (usr.length() <= 20) {
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