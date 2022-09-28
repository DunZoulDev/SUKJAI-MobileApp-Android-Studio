package com.example.SUKJAI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class main__admin_OD extends AppCompatActivity {

    private long backPressedTime;
    private Toast backToast;

    TextView period;

    DBHelper DB;
    ImageView upload_img;
    FloatingActionButton UP;

    int hour;
    int minute;
    public static int hour_s;
    public static int minute_s;
    public static String Time_post;

    Uri image_uri;

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;

    Spinner spinner,Spinner2;
    ArrayList<String> arrayList_Spinner;
    ArrayList<String> arrayList_Spinner2;
    ArrayAdapter<String> arrayAdapter_Spinner;
    ArrayAdapter<String> arrayAdapter_Spinner2;

    static public String ID_USER;
    Button cancel,confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin_od);

        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        upload_img = findViewById(R.id.upload_img);
        UP = findViewById(R.id.chang_img);

        DB = new DBHelper(this);

        Calendar caledar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("ddMMyyhhmmss");
        String dateTime = simpleDateFormat.format(caledar.getTime());
        // Nameus.setText("Guest "+ dateTime);


        UP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(main__admin_OD.this)
                        .crop()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(10);

            }
        });

        //-------   Spinner
        Spinner Spinner = findViewById(R.id.type);
        arrayList_Spinner = new ArrayList<>();
        arrayList_Spinner.add(" ประเภท");
        arrayList_Spinner.add("เสวนา");
        arrayList_Spinner.add("รายการสด");
        arrayList_Spinner.add("เทปเสียง");
        arrayList_Spinner.add("วิดีโอ");
        arrayAdapter_Spinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Spinner);
        Spinner.setAdapter(arrayAdapter_Spinner);

        //-------   Spinner
        Spinner Spinner2 = findViewById(R.id.speaker);
        arrayList_Spinner2 = new ArrayList<>();
        arrayList_Spinner2.add(" วิทยากร/ผู้บรรยาย");
        arrayList_Spinner2.add("พศิน อินทรวงค์");
        arrayList_Spinner2.add("พระเมธีวชิโรดม(ว.วชิรเมธี)");
        arrayList_Spinner2.add("ดังตฤณ");
        arrayList_Spinner2.add("แม่ชีศันสนีย์");
        arrayAdapter_Spinner2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item,arrayList_Spinner2);
        Spinner2.setAdapter(arrayAdapter_Spinner2);

//

//
//
//        //button click
//        upload_img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //if system os is >= marshmallow, request runtime permission
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//                    if (checkSelfPermission(Manifest.permission.CAMERA) ==
//                            PackageManager.PERMISSION_DENIED ||
//                            checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
//                                    PackageManager.PERMISSION_DENIED){
//                        //permission not enabled, request it
//                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                        //show popup to request permissions
//                        requestPermissions(permission, PERMISSION_CODE);
//                    }
//                    else {
//                        //permission already granted
//                        openCamera();
//                    }
//                }
//                else {
//                    //system os < marshmallow
//                    openCamera();
//                }
//            }
//        });
        final Calendar calendar = Calendar.getInstance();
        period = findViewById(R.id.period);
        period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog TimePicker = new TimePickerDialog(main__admin_OD.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(android.widget.TimePicker timePicker, int selectedHour, int selectedMinute) {
                        hour_s = selectedHour;
                        minute_s = selectedMinute;
                        period.setText(selectedHour + ":" + selectedMinute);

                        //post
                        Time_post = period.getText().toString() ;
                    }

                },hour, minute, true);
                TimePicker.show();
            }
        });


        confirm = findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                confirm.startAnimation(animation);

                if (backPressedTime + 2000 > System.currentTimeMillis())
                {
                    Intent intent = new Intent(getApplicationContext(),main__admin.class);
                    intent.putExtra("user_id",ID_USER);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อยืนยันการบันทึกข้อมูล", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                backPressedTime = System.currentTimeMillis();


            }
        });


        cancel = findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein_half);
                cancel.startAnimation(animation);

                if (backPressedTime + 2000 > System.currentTimeMillis())
                {
                    Intent intent = new Intent(getApplicationContext(),main__admin.class);
                    intent.putExtra("user_id",ID_USER);
                    startActivity(intent);
                    finish();
                    return;
                } else {
                    backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อยกเลิกการบันทึกข้อมูล", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                backPressedTime = System.currentTimeMillis();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10) {
            Uri uri = data.getData();
            upload_img.setImageURI(uri);
        }
        else {
            Uri uri = data.getData();
            upload_img.setImageURI(uri);
        }
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        //Camera intent
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE);
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            Intent intent = new Intent(getApplicationContext(), main__admin.class);
            intent.putExtra("user_id",ID_USER);
            startActivity(intent);
            finish();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "กดอีกครั้ง เพื่อยกเลิกการบันทึกข้อมูล", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}