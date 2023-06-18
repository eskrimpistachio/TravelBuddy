package com.example.loginsignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText signUpEmail, signupPassword, userName;
    private Button signupButton;

    private FloatingActionButton backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        TextView textView1 = findViewById(R.id.clickable3);

        String TOR = "By creating an account your agree to our Terms of Service and Privacy Policy";

        SpannableString ss3 = new SpannableString(TOR);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://www.youtube.com/watch?v=xvFZjo5PgG0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                String hexColor = "#439FD9";
                int color = Color.parseColor(hexColor);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                String url = "https://www.youtube.com/watch?v=xvFZjo5PgG0";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                String hexColor = "#439FD9";
                int color = Color.parseColor(hexColor);
                ds.setColor(color);
                ds.setUnderlineText(false);
            }
        };

        ss3.setSpan(clickableSpan1, 41, 57, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss3.setSpan(clickableSpan2, 61, 76, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView1.setText(ss3);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());


        auth = FirebaseAuth.getInstance();
        userName = findViewById(R.id.et_fullname);
        signUpEmail = findViewById(R.id.et_email_sign);
        signupPassword = findViewById(R.id.et_password_sign);
        signupButton = findViewById(R.id.btn_register);
        backButton = findViewById(R.id.fab_back_to_login);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = signUpEmail.getText().toString().trim();
                String pass = signupPassword.getText().toString().trim();
                String name = userName.getText().toString();

                if (name.isEmpty()){
                    userName.setError("Username cannot be empty😥");
                }
                if (user.isEmpty()){
                    signUpEmail.setError("Email cannot be empty😥");
                }
                if (pass.isEmpty()){
                    signupPassword.setError("Password cannot be empty😥");
                }else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(SignUpActivity.this, "Sign Up Successful😎", Toast.LENGTH_SHORT).show();
//                               startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                               startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                               setResult(RESULT_OK);
                               finish();

                           }else {
                               Toast.makeText(SignUpActivity.this, "Sign Up Failed😥" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
            }

        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

}