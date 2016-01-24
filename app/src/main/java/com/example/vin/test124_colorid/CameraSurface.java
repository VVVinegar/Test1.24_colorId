package com.example.vin.test124_colorid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

/**
 * CameraSurface
 *
 * @author: LiPeijing
 * @time: 2016/1/23 9:27
 */

public class CameraSurface extends SurfaceView implements SurfaceHolder.Callback {

    public final static String TAG = "CameraSurface";
    private SurfaceHolder holder;
    protected Camera camera;
    private boolean isPreview;  //判断是否为预览状态

    public CameraSurface(Context context) {
        super(context);
        init();
    }

    public CameraSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init(){
        holder = getHolder();
        holder.addCallback(this);

        isPreview = false;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open(); //打开camera

        if(null != camera){
            try {
                camera.setPreviewDisplay(holder);   //设置预览界面到surface中
            } catch (IOException e) {
                e.printStackTrace();

                camera.release();
                camera = null;
            }
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(getWidth(), getHeight()); //设置camera预览的尺寸
        camera.setParameters(parameters);

        camera.startPreview();
        isPreview = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(null != camera){
            if(isPreview){
                camera.stopPreview();   //停止预览
            }
            camera.release();   //释放camera
            camera = null;
        }
    }
}
