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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ConsumerRegister extends AppCompatActivity implements View.OnClickListener {
    private EditText regEmail, regPass;
    private Button btnReg;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consumer_register);

        regEmail = findViewById(R.id.regCon_email);
        regPass = findViewById(R.id.regCon_password);
        btnReg = findViewById(R.id.btn_register);
        btnReg.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

    }
    private void registerUser() {
        String email = regEmail.getText().toString().trim();
        String password = regPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //Successful registration
                            Toast.makeText(ConsumerRegister.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ConsumerRegister.this, ConsumerLogin.class));
                        } else {
                            Toast.makeText(ConsumerRegister.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == btnReg) {
            registerUser();
        }

    }
}
