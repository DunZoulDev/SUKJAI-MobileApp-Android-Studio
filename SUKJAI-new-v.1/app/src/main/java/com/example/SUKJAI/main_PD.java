package com.example.SUKJAI;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class main_PD extends AppCompatActivity {
    ImageButton topic1, topic2, topic3;
    SliderView sliderView;
    Dialog myDialog;
    List<ImageSliderModel> imageSliderModelList;
    Button bt_schedual,bt_notication,bt_user;

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_p_d);

        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        myDialog = new Dialog(this);

        bt_schedual = findViewById(R.id.bt_schedual);
        bt_notication = findViewById(R.id.bt_notication);
        bt_user = findViewById(R.id.bt_user);

        top_bar_button();

        topic1 = findViewById(R.id.topic_donate);
        topic2 = findViewById(R.id.topic_founda);
        topic3 = findViewById(R.id.topic_volun);

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_nav);
        bottomNavigationView.setSelectedItemId(R.id.pd);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        Intent i = new Intent(getApplicationContext(), main_Home.class);
                        i.putExtra("active_popup","0");
                        i.putExtra("user_id",ID_USER);
                        startActivity(i);
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.od:
                        Intent a = new Intent(getApplicationContext(), main_OD.class);
                        a.putExtra("user_id",ID_USER);
                        startActivity(a);
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.pd:
                        Intent b = new Intent(getApplicationContext(), main_PD.class);
                        b.putExtra("user_id",ID_USER);
                        startActivity(b);
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.ac:
                        Intent c = new Intent(getApplicationContext(), main_AC.class);
                        c.putExtra("user_id",ID_USER);
                        startActivity(c);
                        finish();
                        overridePendingTransition(0,0);
                        return false;

                    case R.id.menu:
                        Intent d = new Intent(getApplicationContext(), main_Menu.class);
                        d.putExtra("user_id",ID_USER);
                        startActivity(d);
                        finish();
                        overridePendingTransition(0,0);
                        return false;
                }
                return false;
            }
        });


        topic1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_PD.this,PD_donation.class);
                startActivity(i);
                finish();
            }
        });
        topic2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_PD.this, PD_fundraising.class);
                startActivity(i);
                finish();
            }
        });
        topic3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(main_PD.this,PD_volunteer.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void top_bar_button() {
        bt_schedual.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                bt_schedual.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), Top_Schedule.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        bt_notication.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                bt_notication.startAnimation(animation);
                popup_underdevelope();
            }
        });

        bt_user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                bt_user.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });
    }

    private void popup_underdevelope(){
        myDialog.setContentView(R.layout.popup_under_develope);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView closeq = myDialog.findViewById(R.id.closeq);
        closeq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(),main_Home.class);
        intent.putExtra("active_popup","0");
        intent.putExtra("user_id",ID_USER);
        startActivity(intent);
        finish();
    }
}
