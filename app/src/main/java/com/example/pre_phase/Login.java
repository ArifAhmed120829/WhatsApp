package com.example.pre_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    Button btn12,btn13;
    EditText et12,et13;
    FirebaseAuth auth2;
    FirebaseUser fbuser;

    @Override
    protected void onStart() {
        super.onStart();
        fbuser = FirebaseAuth.getInstance().getCurrentUser();
        if(fbuser!=null){
            Intent i3 = new Intent(Login.this,MainActivity.class);
            startActivity(i3);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn12 = findViewById(R.id.LoginButton);
        et12 = findViewById(R.id.LoginName);
        et13 = findViewById(R.id.LoginPassword);
        auth2 = FirebaseAuth.getInstance();

        btn13 = findViewById(R.id.RegisterButton);

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent R_now = new Intent(Login.this,RegisterActivity.class);
                startActivity(R_now);
            }
        });
        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = et12.getText().toString();
                String user_pass = et13.getText().toString();
                if(TextUtils.isEmpty(user_name)||TextUtils.isEmpty(user_pass)){
                    Toast.makeText(Login.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth2.signInWithEmailAndPassword(user_name,user_pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(Login.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }

                            });

                }
            }
        });
    }
}