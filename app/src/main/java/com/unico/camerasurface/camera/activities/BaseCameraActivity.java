package com.unico.camerasurface.camera.activities;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.unico.camerasurface.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class BaseCameraActivity extends AppCompatActivity {


    public static final String TAG = "BaseActivity";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        rootView = findViewById(R.id.root_view);
    }

    protected View rootView;

//        private SweetAlertDialog mProgressDialogView;

    public void showToastMessage(String message) {
        try {
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.d(TAG, ex.getMessage());
        }
    }


    public void showSnackbarError(String text) {
        if (text == null) {
            Log.e(TAG, text);
            return;
        }

        Snackbar snackbar = Snackbar.make(getRootView(), text, Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.red_btn_bg_color));
        TextView textView = snackbarView.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
    }


    protected void showFastToast(final String message) {
        try {
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    protected void showToast(final String message) {
        try {
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
            toast.show();
        } catch (Exception ex) {
            Log.d(TAG, ex.toString());
        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getRootView().getWindowToken(), 0);
    }



    private View getRootView() {
        if (rootView == null) {
            rootView = findViewById(R.id.root_view);
        }
        return rootView;
    }

}