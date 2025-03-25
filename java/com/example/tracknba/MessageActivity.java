package com.example.tracknba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;

public class MessageActivity extends AppCompatActivity {

    Button btnsnd;
    EditText msg;
    private FirebaseAuth mAuth;
    FirebaseFirestore fStore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btnsnd = findViewById(R.id.button_send);
        msg = findViewById(R.id.message);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        btnsnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = msg.getText().toString();
                if (message.isEmpty()) {
                    msg.setError("Message cannot be empty");
                }
                else {
                    //                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
                    SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyy_HH:mm:ss", Locale.getDefault());
                    String DAT = sdf.format(new Date());
                    userID = mAuth.getCurrentUser().getUid();
                    DateFormat dateFormat = new SimpleDateFormat("hh.mm aa");
                    String dateString = dateFormat.format(new Date()).toString();
                    String sent = dateString + " : " + message;
                    DocumentReference documentReference = fStore.collection("NBAChatroom").document(DAT);
                    Map<String,Object> chat = new HashMap<>();
                    chat.put("message", sent);
                    //            team.put("favplayers", FieldValue.arrayUnion(playern));
                    documentReference.set(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("TAG", "onSuccess: profile is created for " + userID);
                            startActivity(new Intent(MessageActivity.this, ChatActivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onSuccess: " + e.toString());
                        }
                    });
                }
                //                Write();
            }
        });


    }
    private void Write() {
        //        Date currentTime = Calendar.getInstance().getTime();
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        //        String DAT = sdf.format(new Date());
        String message = msg.getText().toString().trim();
        userID = mAuth.getCurrentUser().getUid();
        //        String fullMessage = userID + " : " + message;
        DocumentReference documentReference = fStore.collection("NBAChatroom").document("Test");
        Map<String,Object> msg = new HashMap<>();
        msg.put("Message", message);
        //            team.put("favplayers", FieldValue.arrayUnion(playern));
        documentReference.set(msg).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("TAG", "onSuccess: profile is created for " + userID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("TAG", "onSuccess: " + e.toString());
            }
        });
    }
}





//package com.example.tracknba;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.Query;
//
//import java.text.SimpleDateFormat;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//import java.util.Calendar;
//import java.util.Date;
//
//public class MessageActivity extends AppCompatActivity {
//
//    Button btnsnd;
//    EditText msg;
//    private FirebaseAuth mAuth;
//    FirebaseFirestore fStore;
//    private FirebaseFirestore firebaseFirestore;
//    String userID;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_message);
//
//        btnsnd = findViewById(R.id.button_send);
//        msg = findViewById(R.id.message);
//        mAuth = FirebaseAuth.getInstance();
//        fStore = FirebaseFirestore.getInstance();
//
//        Query query = firebaseFirestore.collectionGroup(userID);
//
//        btnsnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String message = msg.getText().toString();
//                if (message.isEmpty()) {
//                    msg.setError("Message cannot be empty");
//                }
//                else {
////                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
//                    SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyy_HH:mm:ss", Locale.getDefault());
//                    String DAT = sdf.format(new Date());
//                    userID = mAuth.getCurrentUser().getUid();
//                    String sent = message;
//                    DocumentReference documentReference = fStore.collection("NBAChatroom").document(DAT);
//                    Map<String,Object> chat = new HashMap<>();
//                    chat.put("message", sent);
////            team.put("favplayers", FieldValue.arrayUnion(playern));
//                    documentReference.set(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Log.d("TAG", "onSuccess: profile is created for " + userID);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Log.d("TAG", "onSuccess: " + e.toString());
//                        }
//                    });
//                }
////                Write();
//            }
//        });
//
//
//    }
//    private void Write() {
////        Date currentTime = Calendar.getInstance().getTime();
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
////        String DAT = sdf.format(new Date());
//        String message = msg.getText().toString().trim();
//        userID = mAuth.getCurrentUser().getUid();
////        String fullMessage = userID + " : " + message;
//        DocumentReference documentReference = fStore.collection("NBAChatroom").document("Test");
//        Map<String,Object> msg = new HashMap<>();
//        msg.put("Message", message);
////            team.put("favplayers", FieldValue.arrayUnion(playern));
//        documentReference.set(msg).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Log.d("TAG", "onSuccess: profile is created for " + userID);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Log.d("TAG", "onSuccess: " + e.toString());
//            }
//        });
//    }
//}
