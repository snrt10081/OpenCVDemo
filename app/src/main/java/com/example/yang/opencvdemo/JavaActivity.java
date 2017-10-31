package com.example.yang.opencvdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class JavaActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnProc;
    private ImageView imageView;
    private Bitmap bmp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
        btnProc = (Button) findViewById(R.id.btn_gray_process);
        imageView = (ImageView) findViewById(R.id.image_view);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test);
        imageView.setImageBitmap(bmp);
        btnProc.setOnClickListener(this);
    }

    static {
        System.loadLibrary("native-lib");
    }

    public static native int[] grayProc(int[] pixels, int w, int h);

    @Override
    public void onClick(View v) {

        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        bmp.getPixels(pixels, 0, w, 0, 0, w, h);
        int[] resultInt = grayProc(pixels, w, h);
        Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
        imageView.setImageBitmap(resultImg);
    }

    @Override
    public void onResume(){
        super.onResume();
    }
}
