package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class AC_travel_2_Wat_chol extends AppCompatActivity {
    Button visit_1,visit_2,visit_3,visit_4,back;

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    SliderView sliderView;
    int[] images =
            {
                    R.drawable.place_1_chol_d,
                    R.drawable.place_1_chol_b,
                    R.drawable.place_1_chol_a,
                    R.drawable.place_1_chol_c,
                    R.drawable.place_1_chol_e
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_travel2_wat_chol);

        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        sliderView = findViewById(R.id.image_slider);

        SliderAdapter sliderAdapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        sliderView.startAutoCycle();

        back = findViewById(R.id.back);
        visit_1 = findViewById(R.id.visit_1);
        visit_2 = findViewById(R.id.visit_2);
        visit_3 = findViewById(R.id.visit_3);
        visit_4 = findViewById(R.id.visit_4);

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), main_AC.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        visit_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                visit_1.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), AC_travel_2_Wat_chol_Pano_1.class);
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
                Intent i = new Intent(getApplicationContext(), AC_travel_2_Wat_chol_Pano_2.class);
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
                Intent i = new Intent(getApplicationContext(), AC_travel_2_Wat_chol_Pano_3.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        visit_4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                visit_4.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), AC_travel_2_Wat_chol_Pano_4.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            Intent intent = new Intent(getApplicationContext(),main_AC.class);
            intent.putExtra("user_id",ID_USER);
            startActivity(intent);
            finish();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "?????????????????????????????????????????????????????????", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}