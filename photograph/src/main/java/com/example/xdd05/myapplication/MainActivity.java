package com.example.xdd05.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener {

    private ImageView mImagePhoto;
    private Button mButPhoto;
    private Button mButadd;
    private Bitmap photoBit;
    private int REQUEST_CAPURE_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImagePhoto = (ImageView) findViewById(R.id.iv_photo);
        mButPhoto = (Button) findViewById(R.id.but_photo);
        mButadd = (Button) findViewById(R.id.but_add);
        mButadd.setOnClickListener(this);
        mButPhoto.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.but_photo:
                takeCamera();
                break;
            case R.id.but_add:

                break;
        }
    }

    /**
     * 实现打开系统照相机，并进行拍照。
     */
    public void takeCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, REQUEST_CAPURE_IMAGE);
        }
    }

    /**
     * 获得拍摄的照片，并展示在指定的控件上
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == REQUEST_CAPURE_IMAGE) ;
            {
                Bundle extras = data.getExtras();

                photoBit = (Bitmap) extras.get("data");
                mImagePhoto.setImageBitmap(photoBit);

            }
        }else {
            Toast.makeText(this,"获取资源失败",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
