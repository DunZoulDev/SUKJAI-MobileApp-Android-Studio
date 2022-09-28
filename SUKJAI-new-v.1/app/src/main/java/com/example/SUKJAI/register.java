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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText name,surname,phonenum,password,repassword,email;
    Button confirm,cancel;

    static public String NAME_s ,SURNAME_s ,PHONENUM_s ,PASSWORD_s ,EMAIL_s;

    private long backPressedTime;
    private Toast backToast;

    static public String User_phonenum_value ,User_password_value ,User_fname_value ,User_lname_value ,User_email_value,User_status_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        email = findViewById(R.id.email);

        phonenum = findViewById(R.id.phonenum);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);


        cancel = findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(register.this, R.anim.fadein_half);
                cancel.startAnimation(animation);

                if (backPressedTime + 2000 > System.currentTimeMillis())
                {
                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อยกเลิกการลงทะเบียน", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                backPressedTime = System.currentTimeMillis();
            }
        });

        //------- checkdata -----
        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(register.this, R.anim.fadein_half);
                confirm.startAnimation(animation);

                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(surname.getText().toString()) || TextUtils.isEmpty(email.getText().toString())
                        || TextUtils.isEmpty(phonenum.getText().toString())|| TextUtils.isEmpty(password.getText().toString()) || TextUtils.isEmpty(repassword.getText().toString()) )
                {
                    Toast.makeText(register.this, "โปรดใส่ข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }

                else{

                    NAME_s = name.getText().toString();
                    SURNAME_s = surname.getText().toString();
                    EMAIL_s  = email.getText().toString();

                    PHONENUM_s = phonenum.getText().toString();
                    PASSWORD_s = password.getText().toString();

                    Log.d("12345", NAME_s+"\n");
                    Log.d("12345", SURNAME_s+"\n");
                    Log.d("12345", PHONENUM_s+"\n");
                    Log.d("12345", PASSWORD_s+"\n");


                        if (PASSWORD_s.equals(repassword.getText().toString())){
                            post_data_resgiter();
                            Toast.makeText(register.this, "ลงทะเบียนสำเร็จ", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(register.this, login.class);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(register.this, "รหัสผ่านและรหัสผ่านยืนยันไม่ตรงกัน", Toast.LENGTH_SHORT).show();
                        }
                }
            }
        });

    }

    private void post_data_resgiter() {

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,"http://iiec2312.trueddns.com:19744/user",
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) { Log.i("Response", response);
                        System.out.println(response); }},

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d("volley", "Error: " + error.getMessage());
                        error.printStackTrace();
                        Log.d("Error.Response1", "Unsuccess");
                    }
                }
        )

        {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("User_phonenum", PHONENUM_s);
                params.put("User_password",PASSWORD_s);
                params.put("User_fname",NAME_s);
                params.put("User_lname",SURNAME_s);
                params.put("User_email",EMAIL_s);
                params.put("User_status","User");
                return params;

            }
        };

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        queue.add(jsonObjRequest);
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
            backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อยกเลิกการลงทะเบียน", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}


