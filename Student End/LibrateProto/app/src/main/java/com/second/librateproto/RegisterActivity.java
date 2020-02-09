package com.second.librateproto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {


    public EditText loginEmailId, logInpasswd, regName,regRoll;
    Button btnReg;
    DatabaseReference rootReference= FirebaseDatabase.getInstance().getReference();


    FirebaseAuth firebaseAuth;
    String TAG="LoginActivity";
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnReg=(Button)findViewById(R.id.register_button);
        loginEmailId = findViewById(R.id.register_email);
        logInpasswd = findViewById(R.id.register_password);
        regName=findViewById(R.id.register_name);
        regRoll=findViewById(R.id.register_roll);
        firebaseAuth = FirebaseAuth.getInstance();
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = loginEmailId.getText().toString();
                String userPaswd = logInpasswd.getText().toString();
                String name=regName.getText().toString();
                String roll=regRoll.getText().toString();

                if (userEmail.isEmpty()) {
                    loginEmailId.setError("Provide your Email first!");
                    loginEmailId.requestFocus();
                } else if (userPaswd.isEmpty()) {
                    logInpasswd.setError("Enter Password!");
                    logInpasswd.requestFocus();
                } else if (userEmail.isEmpty() && userPaswd.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(userEmail.isEmpty() && userPaswd.isEmpty())) {
                    signUpWithFirebase(userEmail,userPaswd, name,roll);
                }
            }
        });
    }
    public void signUpWithFirebase(String email,String password, String name, String roll){
        String domain = email .substring(email .indexOf("@") + 1);
        if(domain.equals("iiitl.ac.in")){
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = firebaseAuth.getCurrentUser();

                                Intent I = new Intent(RegisterActivity.this, MajorNavigation.class);
                                startActivity(I);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else{
            Toast.makeText(RegisterActivity.this, "Invalid Email",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
