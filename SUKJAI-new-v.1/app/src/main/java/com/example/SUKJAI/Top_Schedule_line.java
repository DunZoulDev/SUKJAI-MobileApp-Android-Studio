package com.example.SUKJAI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Top_Schedule_line extends AppCompatActivity {
    Button back;
    Button date_table;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_schedule_list);

        back = findViewById(R.id.back);
        date_table = findViewById(R.id.date_table);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Top_Schedule_line.this, main_Home.class);
                startActivity(i);
                finish();
            }
        });

        date_table.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Top_Schedule_line.this, Top_Schedule.class);
                startActivity(i);
                finish();
            }
        });
    }
}