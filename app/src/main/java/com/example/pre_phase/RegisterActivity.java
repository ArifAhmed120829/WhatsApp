package com.example.pre_phase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    Button button1;
    EditText et1,et2,et3;
    TextView tv1;
    FirebaseAuth auth;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        button1 = findViewById(R.id.button);
        et1 = findViewById(R.id.Username);
        et2 = findViewById(R.id.Userpassword);
        et3 = findViewById(R.id.Email);
        tv1 = findViewById(R.id.textView);
        auth = FirebaseAuth.getInstance();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username_text = et1.getText().toString();
                String password_text = et2.getText().toString();
                String email_text = et3.getText().toString();
                if(TextUtils.isEmpty(username_text)||TextUtils.isEmpty(password_text)||TextUtils.isEmpty(email_text)){
                    Toast.makeText(RegisterActivity.this,"Please Fill all fields",Toast.LENGTH_SHORT).show();
                }
                else{
                    RegisterNow(username_text,password_text,email_text);
                }
            }
        });
    }

    private void RegisterNow(final String name, String password, String email){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            ref = FirebaseDatabase.getInstance().getReference("Myuser").child(userid);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",name);
                            hashMap.put("imageURL","default");
                            ref.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(i);
                                        finish();
                                    }

                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                        }


                    }
                });


    }



}
