package com.unico.camerasurface

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.unico.camerasurface.camera.activities.SelfieActivity
import com.unico.camerasurface.camera.iCamera
import com.unico.camerasurface.camera.utils.bitmap.ImageUtils.flipX
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment(), iCamera {

    private lateinit var mContext: Context

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_first, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mContext = view.context

        initListeners()

    }

    private fun initListeners(){
        capture_button.setOnClickListener {
            openCamera()
        }
        imageView.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        val selfieActivity = SelfieActivity()
        selfieActivity.setCameraManager(this)
        startActivity(Intent(activity, selfieActivity::class.java))
    }

    override fun onSuccessCamera(result: String?, bitmap: Bitmap) {
        view_result.visibility = VISIBLE
        imageView.visibility = GONE
        showImageView.setImageBitmap(flipX(bitmap))
    }

    override fun onErrorCamera(errorBio: String?) {
        TODO("Not yet implemented")
    }

    //region Get Camera Permission
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if(requestCode != 10) {
            Toast.makeText(mContext, "Permissão acesso camera negada", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPermission() = arrayOf(Manifest.permission.CAMERA).all{
        ContextCompat.checkSelfPermission(mContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onResume() {
        super.onResume()
        if (!getPermission()) {
            requestPermissions(
                    arrayOf(Manifest.permission.CAMERA),
                    10
            )
        }
    }
    //endregion

}