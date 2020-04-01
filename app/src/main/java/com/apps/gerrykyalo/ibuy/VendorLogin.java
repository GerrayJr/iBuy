package com.apps.gerrykyalo.ibuy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class VendorLogin extends AppCompatActivity implements View.OnClickListener {

    private EditText log_Email, log_Pass;
    private Button btnLogin;
    private TextView tvRegister;
    private FirebaseAuth auth;

    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_login);

        log_Email = findViewById(R.id.ed_email);
        log_Pass = findViewById(R.id.ed_password);
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tv_register);
        tvRegister.setOnClickListener(this);

        dialog = new ProgressDialog(this);

        auth = FirebaseAuth.getInstance();
    }

    private void loginUser() {
        String email = log_Email.getText().toString().trim();
        String password = log_Pass.getText().toString().trim();

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
                            Toast.makeText(VendorLogin.this, "Success", Toast.LENGTH_SHORT).show();
                            Intent prof = new Intent(VendorLogin.this, VendorAddInfo.class);
                            startActivity(prof);
                        } else {
                            Toast.makeText(VendorLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            loginUser();
        }
        if (view == tvRegister) {
            startActivity(new Intent(this, VendorRegister.class));
        }

    }
}
