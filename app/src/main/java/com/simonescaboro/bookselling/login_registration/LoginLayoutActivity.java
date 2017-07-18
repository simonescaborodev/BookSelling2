package com.simonescaboro.bookselling.login_registration;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.simonescaboro.bookselling.R;
import com.simonescaboro.bookselling.app.MainActivity;

import org.w3c.dom.Text;

public class LoginLayoutActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button btn;
    private Button signUp;


    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        setContentView(R.layout.login_layout);

        email = (EditText) findViewById(R.id.emailField);
        password = (EditText) findViewById(R.id.passwordField);
        btn = (Button) findViewById(R.id.loginButton);
        signUp = (Button) findViewById(R.id.signUpButton);

        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
            }
        });

    }

    private void signIn(){

        String email1 = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        if (TextUtils.isEmpty(email1) || TextUtils.isEmpty(password1)) {
            Toast.makeText(this, "Completare i campi", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginLayoutActivity.this, "Credenziali sbagliate", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                }
            });
        }
    }

}
