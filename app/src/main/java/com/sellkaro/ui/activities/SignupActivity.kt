package com.sellkaro.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bhagavad.gita.util.NetworkHandler
import com.sellkaro.R
import com.sellkaro.model.city.City
import com.sellkaro.utils.DevicePermission
import com.sellkaro.utils.EmailChecker
import com.sellkaro.utils.QuendelImagePicker
import com.sellkaro.utils.QuendelTransition
import kotlinx.android.synthetic.main.activity_signup.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class SignupActivity : BaseActivity() {
    var quendelImagePicker: QuendelImagePicker? = null
    val SELECT_CITY_RESQUEST = 103
    var cityModel: City? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        setupToolbar()
        init()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        imgBack.visibility = View.VISIBLE
        imgBack.setOnClickListener { onBackPressed() }
        toolbar_title.text = getString(R.string.signup)
    }

    private fun init() {
        ///quendelImagePicker = QuendelImagePicker.mInstance()
        icPlus.setOnClickListener { openImagePicker() }
        txtCity.setOnClickListener { selectCity() }
        layoutSelectCity.setOnClickListener { selectCity() }
        btn_signup.setOnClickListener { performSignUP() }
    }

    private fun performSignUP() {
        val email = email.text.toString().trim()
        val username = Username.text.toString().trim()
        val password = password.text.toString().trim()
        val city = txtCity.text.toString().trim()
        if (email.isEmpty()) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
        } else if (!EmailChecker.checkEmail(email)) {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show()
        } /*else if (city.isEmpty()) {
            Toast.makeText(this, "Enter City", Toast.LENGTH_SHORT).show()
        } */ else if (NetworkHandler.isNetworkAvailable(this)) {
            performSignUp()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (DevicePermission.hasAllPermissionsGranted(grantResults)) {
            DevicePermission.permissionCallback?.onPermissionGranted()
        }
    }

    private fun openImagePicker() {
        showToast(context, "In process")
        /*quendelImagePicker?.openImagePicker(this, context, {
            Log.e("imageFile ", it.toString())
            try {
                val bitmap = BitmapFactory.decodeFile(it.absolutePath)
                //Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.fromFile(imageFile));
                profile_image.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })*/
    }

    private fun selectCity() {
        startActivityForResult(Intent(this, CityListActivity::class.java), SELECT_CITY_RESQUEST)
        QuendelTransition.setFadeTransitionToActivity(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_CANCELED) return
        when (requestCode) {
            QuendelImagePicker.CHOOSE_PHOTO_INTENT -> {
                try {
                    if (resultCode == Activity.RESULT_OK) {
                        if (data != null && data.data != null) {
                            quendelImagePicker?.handleGalleryResult(data)
                        } else {
                            quendelImagePicker?.handleCameraResult(quendelImagePicker?.cameraUri)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            QuendelImagePicker.SELECTED_IMG_CROP -> {
                try {
                    if (resultCode == Activity.RESULT_OK) {
                        quendelImagePicker?.pickerCallback?.onImagePicked(quendelImagePicker?.imageFile)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            SELECT_CITY_RESQUEST -> {
                cityModel = data?.getParcelableExtra("CITY")
                txtCity.text = cityModel?.name
            }
        }
    }

    private fun performSignUp() {
        startActivity(Intent(this, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        QuendelTransition.setFadeTransitionToActivity(this)
        finish()
    }

}
