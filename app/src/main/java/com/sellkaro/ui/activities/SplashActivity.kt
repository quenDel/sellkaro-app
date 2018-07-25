package com.sellkaro.ui.activities

import android.os.Bundle
import android.os.Handler
import com.sellkaro.constants.Constant
import com.sellkaro.R
import com.sellkaro.utils.AppPreference

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(runnable, 2000)
    }

    private val runnable = Runnable {
        init()
    }

    private fun init() {
        if (AppPreference.getBooleanPreference(context, Constant.isLogin)) {
            proceedToNext(HomeActivity::class)
        } else {
            proceedToNext(LoginSetupActivity::class)
        }
        finish()
    }
}
