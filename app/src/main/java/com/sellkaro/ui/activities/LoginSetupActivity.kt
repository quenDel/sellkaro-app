package com.sellkaro.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bhagavad.gita.util.NetworkHandler
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_login_setup.*
import android.content.pm.PackageManager
import android.util.Base64
import android.view.View
import com.sellkaro.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class LoginSetupActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_setup)

        init()
    }

    private fun init() {
        btnFb.setOnClickListener {
            proceedToNext(HomeActivity::class)
            finish()
        }
        btnGoogle.setOnClickListener {
            proceedToNext(HomeActivity::class)
            finish()
        }
        btnEmail.setOnClickListener {
            proceedToNext(SignupActivity::class)
        }
        btnLogin.setOnClickListener {
            proceedToNext(LoginActivity::class)
        }
    }

}
