package com.unico.camerasurface.camera

import android.graphics.Bitmap


interface iCamera  {

    fun onSuccessCamera(result: String?, bitmap: Bitmap)
    fun onErrorCamera(error: String?)

}