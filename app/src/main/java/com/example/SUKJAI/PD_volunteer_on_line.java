package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PD_volunteer_on_line extends AppCompatActivity {
    Button back;
    static public String ID_USER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_volunteer_on_line);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PD_volunteer.class);
                startActivity(i);
                return ;

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),PD_volunteer.class);
        intent.putExtra("user_id",ID_USER);
        startActivity(intent);
        finish();
    }
}