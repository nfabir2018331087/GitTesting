package com.example.gremaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class AddQuestion extends AppCompatActivity {

    EditText question;
    Button addBtn;
    FirebaseAuth mAuth;
    String currentUserID;
    DatabaseReference reference, allQuestionRef, userQuestionRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null) currentUserID=mAuth.getCurrentUser().getUid();

        allQuestionRef= FirebaseDatabase.getInstance().getReference("all questions");
        userQuestionRef = FirebaseDatabase.getInstance().getReference("user questions").child(currentUserID);
        reference = FirebaseDatabase.getInstance().getReference("users");


        question= (EditText) findViewById(R.id.question);
        addBtn=(Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddQuestion.this)
                        .setMessage("Are you sure you want to Upload this Question?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                addNewQuestion();
                                question.setText("");
                                Toast.makeText(getApplicationContext(),"Question added successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Forum.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();

            }
        });
    }

    public void addNewQuestion() {

        String userQuestion=question.getText().toString();

        if(TextUtils.isEmpty(userQuestion)){
            Toast.makeText(getApplicationContext(),"Please write your question.",Toast.LENGTH_LONG).show();
            return;
        }

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    if(currentUserID!=null) {
                        String name = dataSnapshot.child(currentUserID).child("name").getValue(String.class);
                        //String username = dataSnapshot.child(currentUserID).child("username").getValue(String.class);
                        String userDP = dataSnapshot.child(currentUserID).child("profileimage").getValue(String.class);
                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd.MM.yy", Locale.US);
                        final String saveCurrentDate = currentDate.format(calForDate.getTime());

                        Calendar calForTime = Calendar.getInstance();
                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm", Locale.US);
                        final String saveCurrentTime = currentTime.format(calForTime.getTime());

                        Questions questions = new Questions(name, userDP, userQuestion, saveCurrentTime, saveCurrentDate);

                        String uQKey = userQuestionRef.push().getKey();
                        userQuestionRef.child(uQKey).setValue(questions);

                        questions.setKey(uQKey);
                        String allQKey = allQuestionRef.push().getKey();
                        allQuestionRef.child(allQKey).setValue(questions);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}