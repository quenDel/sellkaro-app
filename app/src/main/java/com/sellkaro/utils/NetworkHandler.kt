package com.bhagavad.gita.util
import android.content.Context
import android.net.ConnectivityManager
import com.sellkaro.ui.activities.BaseActivity
import com.sellkaro.R

class NetworkHandler {

    companion object {
        fun isNetworkAvailable(context: Context?): Boolean {
            context?.let {
                val manager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val info = manager.activeNetworkInfo
                val isConnected = info != null && info.isConnected
                if (!isConnected) {
                    BaseActivity.showToast(context, context.getString(R.string.Please_check_network))
                }
                return isConnected
            }
            return false
        }
    }
} 