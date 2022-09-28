package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PD_fundraising extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    ImageButton child,elderly,disaster,environment;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_fundraising);

        back = findViewById(R.id.back);
        child = findViewById(R.id.gchild);
        elderly = findViewById(R.id.gelderly);
        disaster = findViewById(R.id.gdisaster);
        environment = findViewById(R.id.genvironment);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_fundraising.this, main_PD.class);
                startActivity(i);
                finish();
            }
        });


        child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_fundraising.this, PD_fundraising_clide.class);
                startActivity(i);
                return;

            }
        });

        elderly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_fundraising.this, PD_fundraising_elderly.class);
                startActivity(i);
                return;

            }
        });
        disaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_fundraising.this, PD_fundraising_disaster.class);
                startActivity(i);
                return;

            }
        });
        environment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PD_fundraising.this, PD_fundraising_environment.class);
                startActivity(i);
                return;

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