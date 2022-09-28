package com.example.SUKJAI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    EditText Username,Password;
    Button login,guest;
    TextView regis;
    ImageView facebook,gmail;

    private long backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        login = findViewById(R.id.login);


        regis = findViewById(R.id.regis);
        regis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(login.this, R.anim.fadein_half);
                regis.startAnimation(animation);

                Intent i = new Intent(login.this, register.class);
                startActivity(i);
                finish(); }
        });


        guest  = findViewById(R.id.Guest);
        guest.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, main_Home.class);
                i.putExtra("user_id","0");  //send data
                i.putExtra("active_popup","1");
                startActivity(i);
                finish(); }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(login.this, R.anim.fadein_half);
                login.startAnimation(animation);

                if (Username.getText().toString().equals("1111") && Password.getText().toString().equals("123456")) {
                    Intent intent = new Intent(getApplicationContext(), main_Home.class);
                    intent.putExtra("user_id","0");  //send data
                    intent.putExtra("active_popup","1");
                    startActivity(intent);
                    finish();
                }

                if (TextUtils.isEmpty(Username.getText().toString()) || TextUtils.isEmpty(Password.getText().toString())) {

                        if (backPressedTime + 2000 > System.currentTimeMillis())
                        {
                            return;
                        } else {
                            backToast = Toast.makeText(getBaseContext(), "โปรดใส่บัญชีผู้ใช้และรหัสผ่านให้ครบ", Toast.LENGTH_SHORT);
                            backToast.show();
                        }
                        backPressedTime = System.currentTimeMillis();
                }

                else
                {
                    jsonLogin();
                }
            }
        });

        facebook = findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "อยู่ในระหว่างการพัฒนา", Toast.LENGTH_SHORT).show();
            }
        });

        gmail = findViewById(R.id.gmail);
        gmail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(login.this, "อยู่ในระหว่างการพัฒนา", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void jsonLogin() {
        final String url = "http://iiec2312.trueddns.com:19744/users";
        RequestQueue queue = Volley.newRequestQueue(this);

        String user = Username.getText().toString();
        String password = Password.getText().toString();
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("78945642134", response.toString());
                        try
                        {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);

                                String ID = employee.getString("User_id");
                                String user_phone_get = employee.getString("User_phonenum");
                                String password_get = employee.getString("User_password");
                                String User_status_get = employee.getString("User_status");

                                if (user.equals(user_phone_get) && password.equals(password_get) && User_status_get.equals("admin"))
                                {
                                    Intent intent = new Intent(getApplicationContext(), main__admin.class);
                                    intent.putExtra("user_id",ID);  //send data
                                    startActivity(intent);
                                    finish();
                                }

                                else if (user.equals(user_phone_get) && password.equals(password_get) && User_status_get.equals("user"))
                                {
                                    Intent intent = new Intent(getApplicationContext(),main_Home.class);
                                    intent.putExtra("user_id",ID);  //send data
                                    intent.putExtra("active_popup","1");
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            finish();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        queue.add(getRequest);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "กดอีกครั้งเพื่อออก", Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();
    }

}




























