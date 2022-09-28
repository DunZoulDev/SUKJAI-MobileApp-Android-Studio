package com.example.SUKJAI;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//import com.github.dhaval2404.imagepicker.ImagePicker;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity {
    Button bp,login,guest;
    ImageView cover,profile;
    FloatingActionButton fab,chang;
    TextView Nameus,Surus;
    EditText name,lastname;

    Dialog myDialog;

    Button backhome;
    LinearLayout add_profile,switch_profile,logout;

    TextView name_user_TxVi,  phonenum_user_TxVi, email_TxVi, update_TxVi ;
    static public String F_name_user_s, L_name_user_s,  phonenum_user_s, email_user_s, update_user_s;

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        myDialog = new Dialog(this);
        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);


        name_user_TxVi = findViewById(R.id.name_user);
        phonenum_user_TxVi = findViewById(R.id.user_phone);
        email_TxVi = findViewById(R.id.email);
        update_TxVi  = findViewById(R.id.user_update);

        get_data_user();

        add_profile = findViewById(R.id.add_profile);
        add_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_underdevelope();
            }
        });

        switch_profile = findViewById(R.id.switch_profile);
        switch_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_underdevelope();
            }
        });

        logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        backhome = findViewById(R.id.backhome);
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),main_Home.class);
                intent.putExtra("active_popup","0");
                intent.putExtra("user_id",ID_USER);
                startActivity(intent);
                finish();
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
                                phonenum_user_TxVi.setText("-");
                                email_TxVi.setText("-");
                                update_TxVi.setText("00-00-00");
                            }

                            else if ( object_user.length() >= 1 )
                            {
                                F_name_user_s = object_user.getString("User_fname");
                                L_name_user_s = object_user.getString("User_lname");

                                email_user_s = object_user.getString("User_email");
                                phonenum_user_s = object_user.getString("User_phonenum");

                                name_user_TxVi.setText(F_name_user_s+" "+L_name_user_s);
                                phonenum_user_TxVi.setText(phonenum_user_s);
                                email_TxVi.setText(email_user_s);

                                String last_update = object_user.getString("Stamp_update");
                                update_user_s = last_update;
                                String trim = update_user_s.substring(0,10);

                                update_TxVi.setText("อัปเดตเมื่อ "+trim);
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