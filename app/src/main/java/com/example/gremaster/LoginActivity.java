package com.example.gremaster;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText, passEditText;
    private Button logInButton;
    private TextView textView;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference();

        logInButton = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView6);
        emailEditText = (EditText) findViewById(R.id.editTextTextEmailAddress);
        passEditText = (EditText) findViewById(R.id.editTextTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        textView.setOnClickListener(this);
        logInButton.setOnClickListener(this);

        //getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.textView6){
            Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(intent);
        }
        if(v.getId()==R.id.button) userLogIn();
    }

    private void userLogIn() {

        String email = emailEditText.getText().toString().trim();
        String password = passEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Enter an email address");
            emailEditText.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passEditText.setError("Enter a password");
            passEditText.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passEditText.setError("Password length should be 6 or higher");
            passEditText.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            //dataPass();
                            finish();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                        } else Toast.makeText(LoginActivity.this, "LogIn Unsuccessful", Toast.LENGTH_LONG).show();
                    }
                });

    }

    /*public void dataPass(){
        String email = emailEditText.getText().toString().trim();

        Query checkUser = databaseReference.orderByChild("email").equalTo(email);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String key = snapshot.getRef().getParent().getKey();



                    assert key != null;
                    String nameFromDB = snapshot.child(key).child("name").getValue().toString();
                    String userNameFromDB = snapshot.child(key).child("username").getValue().toString();
                    String emailFromDB = snapshot.child(key).child("email").getValue().toString();
                    String expertFromDB = snapshot.child(key).child("expert").getValue().toString();

                    Log.d("NAME",nameFromDB);


                    intent.putExtra("name",nameFromDB);
                    intent.putExtra("username",userNameFromDB);
                    intent.putExtra("email",emailFromDB);
                    intent.putExtra("expert",expertFromDB);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
}