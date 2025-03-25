package com.example.tracknba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
        Button btnmsg;
        EditText msg;
        FirebaseFirestore fStore;
        TextView username;
        String userID;
        private FirebaseAuth mAuth;
        private FirebaseFirestore firebaseFirestore;
        private RecyclerView messageRecycler;
        private FirestoreRecyclerAdapter adapter;
        String NBAChatroom;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_chat);
                btnmsg = findViewById(R.id.button_message);
                fStore = FirebaseFirestore.getInstance();
                mAuth =FirebaseAuth.getInstance();
                msg = findViewById(R.id.message);
                username = findViewById(R.id.username);
//                usr = findViewById(R.id.user_info);

                userID = mAuth.getCurrentUser().getUid();

                DocumentReference documentReference = fStore.collection("Username").document(userID);
                documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                             username.setText(value.getString("User"));
                        }
                });



                btnmsg.setOnClickListener(new View.OnClickListener() {
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
                                        DateFormat dateFormat = new SimpleDateFormat("MM/dd hh:mm aa");
                                        String dateString = dateFormat.format(new Date()).toString();
                                        String text = username.getText().toString();
                                        String sent =  text + "  " + dateString + "\n" + message;
                                        DocumentReference documentReference = fStore.collection("NBAChatroom").document(DAT);
                                        Map<String,Object> chat = new HashMap<>();
                                        chat.put("message", sent);
                                        //            team.put("favplayers", FieldValue.arrayUnion(playern));
                                        documentReference.set(chat).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                        msg.getText().clear();
                                                        Log.d("TAG", "onSuccess: profile is created for ");
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


                mAuth = FirebaseAuth.getInstance();

//              NBAChatroom = mAuth.getCurrentUser().getUid();
                firebaseFirestore = FirebaseFirestore.getInstance();
                messageRecycler = findViewById(R.id.messageRecycler);
                Query query = firebaseFirestore.collectionGroup("NBAChatroom");
//                query = firebaseFirestore.collectionGroup(NBAChatroom);

                FirestoreRecyclerOptions<ChatListModel> options = new FirestoreRecyclerOptions.Builder<ChatListModel>()
                        .setQuery(query, ChatListModel.class).build();
                adapter = new FirestoreRecyclerAdapter<ChatListModel, ChatViewHolder>(options) {
                        @Override
                        protected void onBindViewHolder(@NonNull ChatViewHolder holder, int position, @NonNull ChatListModel model) {
                                holder.message.setText(model.getMessage());
                        }

                        @NonNull
                        @Override
                        public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_singles, parent, false);
                                return new ChatViewHolder(view);
                        }


                };
                messageRecycler.setHasFixedSize(true);
                messageRecycler.setLayoutManager(new LinearLayoutManager(this));
                messageRecycler.setAdapter(adapter);
        }


        //                btnmsg.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                                startActivity(new Intent(ChatActivity.this, MessageActivity.class));
//                        }
//                });
        private class ChatViewHolder extends RecyclerView.ViewHolder {
                //        private TextView list_name;
//        private ImageView list_image;
                private TextView message;

//        private TextView list_desc;

                public ChatViewHolder(@NonNull View itemView) {
                        super(itemView);
                        message = itemView.findViewById(R.id.message);
//                list_name = itemView.findViewById(R.id.list_text);

//            list_desc = itemView.findViewById(R.id.list_player);
                }

        }

        @Override
        protected void onStop() {
                super.onStop();
                adapter.stopListening();
        }

        @Override
        public void onStart() {
                super.onStart();
                adapter.startListening();
        }
}

