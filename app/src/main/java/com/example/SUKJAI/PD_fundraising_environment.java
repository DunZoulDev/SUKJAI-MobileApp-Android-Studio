package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PD_fundraising_environment extends AppCompatActivity {
    Button back;
    static public String ID_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_fundraising_environment);


        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),PD_fundraising.class);
                startActivity(i);
                return ;

            }
        });
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),PD_fundraising.class);
        intent.putExtra("user_id",ID_USER);
        startActivity(intent);
        finish();
    }

}