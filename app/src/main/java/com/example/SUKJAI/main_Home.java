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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

public class main_Home extends AppCompatActivity {
    SliderView sliderView;
    Dialog myDialog;
    ImageButton hot_360_1;
    Button bt_schedual,bt_notication,bt_user;

    Button visit_1,visit_2,visit_3;

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;
    static public String POP_UP_intro_sign="0";

    static public int count_next_popup = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        POP_UP_intro_sign = _intent.getStringExtra("active_popup");

        Log.d("21212", "pop up is "+POP_UP_intro_sign);
        Log.d("12345689", "user id is "+ID_USER);

        myDialog = new Dialog(this);

        // get ค่า จากหน้า login
        if(POP_UP_intro_sign.equals("1"))
        {
            popup_intro();
        }

        visit_1 = findViewById(R.id.visit_1);
        visit_2 = findViewById(R.id.visit_2);
        visit_3 = findViewById(R.id.visit_3);

        bt_schedual = findViewById(R.id.bt_schedual);
        bt_notication = findViewById(R.id.bt_notication);
        bt_user = findViewById(R.id.bt_user);

        top_bar_button();

        visit_place_360();

        //----------------------------------- set variable LINK ------------------------------------

        // link
//        hot_360_1 = findViewById(R.id.img_home_hot_1);
//        hot_360_1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Intent i = new Intent(main_Home.this, webview_360_1.class);
//                startActivity(i);
//                finish();
//            }
//        });

        //-------------------------------------- Link ----------------------------------------------
//        hot_360_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoUrl("https://360.kaenkaewontour.com/watarun");
//            }
//        });

//       //-------------------------------------- Navigation -----------------------------------------

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);
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

    }

    private void visit_place_360() {
        visit_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                visit_1.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), AC_travel_1_Sathira.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        visit_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                visit_2.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), AC_travel_2_Wat_chol.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        visit_3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                visit_3.startAnimation(animation);
                popup_underdevelope();
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

    private void popup_intro(){
        myDialog.setContentView(R.layout.popup_intro);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        ImageView img = myDialog.findViewById(R.id.popup_intro_img);
        ImageView slide_view = myDialog.findViewById(R.id.popup_intro_sliding);
        TextView text = myDialog.findViewById(R.id.popup_intro_description);

        TextView close = myDialog.findViewById(R.id.popup_intro_closeq);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_next_popup =1;
                myDialog.dismiss();
            }
        });

        ImageView next = myDialog.findViewById(R.id.popup_intro_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count_next_popup = count_next_popup+1;

                if(count_next_popup == 2)
                {
                    img.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_img_2));
                    slide_view.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_slide_2));
                    text.setText("ไม่ว่าจะอยู่ที่ไหน เมื่อไหร่\n"+
                            "ก็ทำบุญกับเราได้ผ่าน\n" +
                            "Digital merit activity \n");
                }

                if(count_next_popup == 3)
                {
                    img.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_img_3));
                    slide_view.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_slide_3));
                    text.setText("ทำทานรูปแบบใหม่\n" +
                            "พึ่งตนเองได้ ให้คนอื่นเป็น\n");
                }

                if(count_next_popup == 4)
                {
                    img.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_img_4));
                    slide_view.setImageDrawable(getResources().getDrawable(R.drawable.popup_intrto_slide_4));
                    text.setText("เข้าถึงคอนเทนต์ได้หลากหลาย\n" +
                            "จากหลากหลายวิทยากร\n"+
                            "ตลอด 24 ชั่วโมง\n");
                }

                if(count_next_popup == 5)
                {
                    count_next_popup =1;
                    myDialog.dismiss();
                }
            }
        });

        myDialog.show();
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


    //-------------------------------------- Function goto URL -----------------------------------------
//    private void gotoUrl(String s) {
//        Uri uri = Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW,uri));
//    }


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


