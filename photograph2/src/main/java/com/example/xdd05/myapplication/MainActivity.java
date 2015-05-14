package com.example.xdd05.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.xdd05.myapplication.R.mipmap.ic_launcher;

public class MainActivity extends Activity {


    private File tempFile = new File(Environment.getExternalStorageDirectory(),getPhotoFileName());


    private Button mButton;
    private ImageView mimage;
    private TextView mText;

    private static final int PHOTO_REQUEST_TAKEPHOT = 1; //拍照
    private static final int PHOTO_REQUEST_GALLERY = 2; //从相册选择
    private static final int PHOTO_REQUEST_CUT = 3; //结果
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mButton = (Button) findViewById(R.id.btn_creama);
        mimage = (ImageView) findViewById(R.id.img_creama);
        mText = (TextView) findViewById(R.id.text);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用系统照相机
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //指定调用照相机拍照后照片的存储路径,MediaStore.EXTRA_OUTPUT 输出原图
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
                startActivityForResult(intent, PHOTO_REQUEST_TAKEPHOT);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case PHOTO_REQUEST_TAKEPHOT:
//                if (data != null)
                    sentPicToNext(data);
                break;
            /*case PHOTO_REQUEST_GALLERY:
                if (data != null)
                    startPhotoZoom(data.getData());
                break;
            case PHOTO_REQUEST_CUT:
                if (data != null)
                    sentPicToNext(data);
                break;*/
        }

    }

    /**
     * 对图片进行剪裁
     * @param uri 图片存储地Uri
     */
    private void startPhotoZoom(Uri uri){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //crop为true是设置在开启的Intent中设置显示的view可剪裁
        //intent.putExtra("crop","false");
        //aspectX，aspectY 是宽高比例
        //intent.putExtra("aspectX",2);
       // intent.putExtra("aspectY",3);
        //outputX，outputY 是剪裁图片的宽高
        intent.putExtra("outputX",200);
        intent.putExtra("outputY",300);
        intent.putExtra("return-data",true);
        intent.putExtra("nofaceDetection",true);
        startActivityForResult(intent,PHOTO_REQUEST_CUT);
    }

    /**
     * 讲处理后的图片传递到下一个界面处理
     * @param picdata
     */
    private void sentPicToNext(Intent picdata){
        Bundle bundle = picdata.getExtras();
        if (bundle != null) {
           // Bitmap photo = bundle.getParcelable("data");
            Bitmap photo = (Bitmap) bundle.get("data");
            if (photo == null) {
                Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show();
            } else {
                Drawable drawable = new BitmapDrawable(this.getResources(), photo);
                mimage.setImageDrawable(drawable);
                mText.setText(tempFile.getAbsolutePath());
            }
        }
    }
    //使用系统当前日期加以调整座位照片的名称
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dataFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
        return dataFormat.format(date)+".jpg";
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
