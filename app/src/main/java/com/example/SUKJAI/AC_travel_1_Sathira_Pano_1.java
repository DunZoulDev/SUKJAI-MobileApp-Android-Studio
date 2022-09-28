package com.example.SUKJAI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.io.InputStream;

public class AC_travel_1_Sathira_Pano_1 extends AppCompatActivity {
    private VrPanoramaView mVRPanoramaView;

    private long backPressedTime;
    private Toast backToast;

    static public String ID_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_travel1_sathira_pano1);

        //get id----
        Intent _intent = getIntent();
        ID_USER = _intent.getStringExtra("user_id");
        Log.d("12345689", "user id is "+ID_USER);

        mVRPanoramaView = (VrPanoramaView) findViewById(R.id.vrPanoramaView);
        loadPhotoSphere();

    }
    @Override
    protected void onPause() {
        super.onPause();
        mVRPanoramaView.pauseRendering();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVRPanoramaView.resumeRendering();
    }

    @Override
    protected void onDestroy() {
        mVRPanoramaView.shutdown();
        super.onDestroy();
    }


    private void loadPhotoSphere() {
        VrPanoramaView.Options options = new VrPanoramaView.Options();
        InputStream inputStream = null;

        AssetManager assetManager = getAssets();
        try {
//            inputStream = assetManager.open("sathira_panarama_1.jpg");
//          Bitmap inputPANO = BitmapFactory.decodeResource(getResources(), R.drawable.sathira_panarama_1);
            options.inputType = VrPanoramaView.Options.TYPE_MONO;
            mVRPanoramaView.loadImageFromBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.sathira_panarama_1), options);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            Intent intent = new Intent(getApplicationContext(),AC_travel_1_Sathira.class);
            intent.putExtra("user_id",ID_USER);
            startActivity(intent);
            finish();
            return;
        } else {
            backToast = Toast.makeText(getBaseContext(), "กดอีกครั้งเพื่อกลับ", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


}