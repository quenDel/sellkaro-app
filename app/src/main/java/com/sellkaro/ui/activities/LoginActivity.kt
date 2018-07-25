package com.sellkaro.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bhagavad.gita.util.NetworkHandler
import com.sellkaro.R
import com.sellkaro.utils.EmailChecker
import com.sellkaro.utils.QuendelTransition
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        setupToolbar()
        init()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        imgBack.visibility = View.VISIBLE
        imgBack.setOnClickListener { onBackPressed() }
        toolbar_title.text = getString(R.string.signin)
    }

    private fun init() {
        forgotpassword.setOnClickListener { showToast(this, "In Process") }
        btn_login.setOnClickListener {
            val email = email.text.toString().trim()
            val password = password.text.toString().trim()
            if (email.isEmpty()) {
                Toast.makeText(this, "Enter email or username", Toast.LENGTH_SHORT).show()
            } else if (password.isEmpty()) {
                Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
            } else if (NetworkHandler.isNetworkAvailable(this)) {
                performLogin()
            }
        }
    }

    fun performLogin() {
        startActivity(Intent(this, HomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP))
        QuendelTransition.setFadeTransitionToActivity(this)
        finish()
    }
}
