package com.apps.gerrykyalo.ibuy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class ConsumerLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText logEmail, logPass;
    private Button logBtn;
    private TextView tvRegister;

    private FirebaseAuth auth;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_login);

        logEmail = findViewById(R.id.con_email);
        logPass = findViewById(R.id.con_password);
        logBtn = findViewById(R.id.btn_login);
        logBtn.setOnClickListener(this);
        tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);

        dialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
    }

    private void loginUser() {
        String email = logEmail.getText().toString().trim();
        String password = logPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password!!", Toast.LENGTH_SHORT).show();
            return;
        }

        dialog.setMessage("Please Wait");
        dialog.show();

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            Toast.makeText(ConsumerLogin.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent prof = new Intent(ConsumerLogin.this, ConsumerMap.class);
                            startActivity(prof);
                        } else {
                            Toast.makeText(ConsumerLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == logBtn) {
            loginUser();
        }
        if (view == tvRegister) {
            startActivity(new Intent(this, ConsumerRegister.class));
        }

    }

}
