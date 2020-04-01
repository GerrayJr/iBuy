package com.apps.gerrykyalo.ibuy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VendorAddInfo extends AppCompatActivity implements View.OnClickListener {
    private EditText infName, infUser, infPhone, conPhone;
    private Spinner sLocation;
    private Button btnContinue;

    private ProgressDialog dialog;
    private DatabaseReference databaseReference,dbrefer;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vendor_add_info);

        infName = findViewById(R.id.info_name);
        infUser = findViewById(R.id.info_username);
        infPhone = findViewById(R.id.info_phone);
        conPhone = findViewById(R.id.confirm_phone);
        sLocation = findViewById(R.id.loc_spin);
        btnContinue = findViewById(R.id.info_continue);
        btnContinue.setOnClickListener(this);

        dialog = new ProgressDialog(this);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Vendors");
        dbrefer = FirebaseDatabase.getInstance().getReference().child("Buyers");
        firebaseAuth = FirebaseAuth.getInstance();
    }
    private void saveInfo()
    {
        dialog.setMessage("Saving Information");
        dialog.show();
        String name = infName.getText().toString().trim();
        String user = infUser.getText().toString().trim();
        int phone = Integer.parseInt(infPhone.getText().toString().trim());
        //int cphone = Integer.parseInt(conPhone.getText().toString().trim());
        String location = sLocation.getSelectedItem().toString().trim();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(user)) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(location)) {
            Toast.makeText(this, "SelectLocation", Toast.LENGTH_SHORT).show();
            return;
        }

        InfoVendor infoVendor = new InfoVendor(name,user,phone,location);
        InfoVendor infoVend= new InfoVendor(name,user,phone,location);
        FirebaseUser userl = firebaseAuth.getCurrentUser();

        databaseReference.child(userl.getUid()).setValue(infoVendor);
        dbrefer.child(userl.getUid()).setValue(infoVend);
        dialog.dismiss();
        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view == btnContinue)
        {
            saveInfo();
        }

    }
}
