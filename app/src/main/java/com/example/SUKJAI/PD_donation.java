package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PD_donation extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    Button back;
    ImageButton temple,hospital,school;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_donation);

        temple = findViewById(R.id.gtemple);
        hospital = findViewById(R.id.ghospital);
        school = findViewById(R.id.gschool);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_donation.this, main_PD.class);
                startActivity(i);
                return ;

            }
        });

        temple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_donation.this, PD_donation_temple.class);
                startActivity(i);
                return ;

            }
        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_donation.this, PD_donation_hospital.class);
                startActivity(i);
                return ;

            }
        });
        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_donation.this, Pd_donation_school.class);
                startActivity(i);
                return ;

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),main_PD.class);
        intent.putExtra("user_id",ID_USER);
        startActivity(intent);
        finish();
    }

}