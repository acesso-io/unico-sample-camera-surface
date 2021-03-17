package com.unico.camerasurface.camera.activities;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.unico.camerasurface.R;
import com.unico.camerasurface.camera.iCamera;
import com.unico.camerasurface.camera.utils.camera.CaptureImageProcessor;
import com.unico.camerasurface.camera.utils.camera.ImageProcessor;


public class SelfieActivity extends Camera2BaseActivity implements ImageProcessor, CaptureImageProcessor {

    private Bitmap bitmapToUse;
    private String base64ToUse;
    boolean isLoadingProcess = false;
    private RelativeLayout takePictureImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfie);
        textureView = findViewById(R.id.texture);

        super.activity = SelfieActivity.this;
        super.imageProcessor = this;
        super.captureImageProcessor = this;

        initViews();
        initListeners();

    }


    private void initViews(){
        takePictureImageButton = findViewById(R.id.take_picture);
        //ivPreview = findViewById(R.id.ivPreview);
    }

    private void initListeners(){
        takePictureImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });
    }

    protected void setMaxSizes(){
        Point displaySize = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(displaySize);
        SCREEN_HEIGHT = displaySize.x;
        SCREEN_WIDTH = displaySize.y;
    }

    protected void takePicture() {

        if (DEBUG) { Log.d(TAG, "Take picture"); }

        if (previewRequestBuilder == null || captureSession == null) {
            return;
        }

        try {
            // This is how to tell the camera to trigger.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                previewRequestBuilder.set(
                        CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER,
                        CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_START);
            }

            // Tell #captureCallback to wait for the precapture sequence to be set.
            state = STATE_WAITING_PRECAPTURE;

            captureSession.capture(
                    previewRequestBuilder.build(),
                    captureCallback,
                    backgroundHandler);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void capture(String base64) {

    }

    @Override
    public void capture(String base64, Bitmap bitmap) {
        if (base64 != null) {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    bitmapToUse = bitmap;
                    base64ToUse = base64;
                    isLoadingProcess = true;

                    icamera.onSuccessCamera(base64,bitmap);

                }
            });

        } else {
            Log.d(TAG, "Erro ao recuperar imagem capturada");
            icamera.onErrorCamera("Erro ao recuperar imagem capturada");
        }
        finish();
    }

    @Override
    public void process(byte[] image, int w, int h, int f) {

    }

    @Override
    public void onBackPressed() {

        if(DEBUG) {
            System.out.println("BACK PRESSED");
        }
        activity.finish();
    }

    public void onStop() {
        super.onStop();

    }

    public void onResume() {
        super.onResume();

    }

    public void setCameraManager(Fragment context) throws Exception {
        if(context instanceof iCamera){
            super.icamera = (iCamera) context;
        }else {
            throw new Exception("iCamera needed");
        }
    }


}