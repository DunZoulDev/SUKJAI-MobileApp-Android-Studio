package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class main__admin extends AppCompatActivity {
    LinearLayout add_PD,add_OD,add_DM;
    private long backPressedTime;
    private Toast backToast;

    Button dataAdmin,logout;
    static public String ID_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        add_PD = findViewById(R.id.add_PD);
        add_OD = findViewById(R.id.add_OD);
        add_DM = findViewById(R.id.add_DM);

        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        dataAdmin = findViewById(R.id.dataAdmin);
        dataAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                dataAdmin.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), main__admin_profile.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                logout.startAnimation(animation);
                if (backPressedTime + 2000 > System.currentTimeMillis())
                {
                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อออกจากระบบ", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                backPressedTime = System.currentTimeMillis();
            }
        });


        add_PD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                add_PD.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), main__admin_PD.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        add_OD.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                add_OD.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), main__admin_OD.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        add_DM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                add_DM.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), main__admin_DM.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            Intent intent = new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            finish();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อออกจากระบบ", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

}