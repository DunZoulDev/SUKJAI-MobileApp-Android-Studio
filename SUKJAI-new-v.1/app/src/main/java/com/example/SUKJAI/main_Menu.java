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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class main_Menu extends AppCompatActivity {
    SliderView sliderView;
    List<ImageSliderModel> imageSliderModelList;
    Button lin;
    TextView Nmu;
    private long backPressedTime;
    private Toast backToast;
    Button bt_schedual,bt_notication,bt_user,bt_user_data;
    Dialog myDialog;
    static public String ID_USER;

    TextView see_user_data;
    TextView name_user_TxVi;
    static public String F_name_user_s, L_name_user_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        myDialog = new Dialog(this);

        bt_schedual = findViewById(R.id.bt_schedual);
        bt_notication = findViewById(R.id.bt_notication);
        bt_user = findViewById(R.id.bt_user);

        top_bar_button();
        get_data_user();

        name_user_TxVi = findViewById(R.id.name_user);

        see_user_data = findViewById(R.id.see_user_data);
        see_user_data.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                see_user_data.startAnimation(animation);
                Intent i = new Intent(getApplicationContext(), Profile.class);
                i.putExtra("user_id",ID_USER);
                startActivity(i);
                finish(); }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case R.id.home:
                        Intent i = new Intent(getApplicationContext(), main_Home.class);
                        i.putExtra("user_id",ID_USER);
                        i.putExtra("active_popup","0");
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

    private void get_data_user(){

        Log.d("12345689", "in");

        final String url = "http://iiec2312.trueddns.com:19744/user/"+ID_USER;
        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            JSONObject object_user = response.getJSONObject("data");

                            if ( object_user.length() == 0 ) {

                                name_user_TxVi.setText("บัญชีเยี่ยมชม");
                            }

                            else if ( object_user.length() >= 1 )
                            {
                                F_name_user_s = object_user.getString("User_fname");
                                L_name_user_s = object_user.getString("User_lname");

                                name_user_TxVi.setText(F_name_user_s+" "+L_name_user_s);
                            }

//                            age_user_s = object_user.getString("User_Lname");
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                        Log.d("12345689", response.toString());
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(getRequest);
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
