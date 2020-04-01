package com.apps.gerrykyalo.ibuy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnConsume, btnVendor, btnRider;
    private ImageView imgHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConsume = findViewById(R.id.st_consumer);
        btnVendor = findViewById(R.id.st_vendor);
        btnRider = findViewById(R.id.st_rider);
        imgHelp = findViewById(R.id.img_help);

        btnConsume.setOnClickListener(this);
        btnVendor.setOnClickListener(this);
        btnRider.setOnClickListener(this);
        imgHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnVendor) {
            startActivity(new Intent(MainActivity.this, VendorLogin.class));
            //finish();
        }
        if (view == btnConsume) {
            startActivity(new Intent(this, ConsumerLogin.class));
            //finish();
        }
        if (view == btnRider) {
            startActivity(new Intent(this, RiderActivity.class));
            //finish();
        }

    }
}
