package com.example.vin.test124_colorid;

import android.graphics.Color;
import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PreviewSurface.OnColorListener {

    private CircleCrossView crossView;  //颜色环
    private PreviewSurface previewSurface;  //camera预览

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        crossView = (CilcleCrossView) findViewById(R.id.cross_view);
//
//        crossView.setColor(Color.GREEN);
//        crossView.refresh();

        crossView = (CircleCrossView) findViewById(R.id.cross_view);
        previewSurface = (PreviewSurface) findViewById(R.id.preview_surface);

        //设置颜色设置监听器
        previewSurface.setOnColorListener(this);

    }

    public void onColor(int color){
        crossView.setColor(color);
        crossView.refresh();
    }
}

