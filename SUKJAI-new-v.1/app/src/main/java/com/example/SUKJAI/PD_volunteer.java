package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class PD_volunteer extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;


    Button back;
    ImageButton topic1, topic2, topic3,topic4;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_volunteer);

        back = findViewById(R.id.back);
        topic1 = findViewById(R.id.topic_know);
        topic2 = findViewById(R.id.topic_put);
        topic3 = findViewById(R.id.topic_online);
        topic4 = findViewById(R.id.topic_offline);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_volunteer.this, main_PD.class);
                startActivity(i);
                finish();
            }
        });

        topic1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_volunteer.this, PD_volunteer_knowladge.class);
                startActivity(i);
                finish();
            }
        });

        topic2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_volunteer.this, PD_volunteer_putta_arsa.class);
                startActivity(i);
                finish();
            }
        });

        topic3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_volunteer.this, PD_volunteer_on_line.class);
                startActivity(i);
                finish();
            }
        });

        topic4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(PD_volunteer.this, PD_volunteer_off_line.class);
                startActivity(i);
                finish();
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