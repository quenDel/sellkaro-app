package com.sellkaro.ui.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.sellkaro.constants.Constant
import com.sellkaro.R
import com.sellkaro.retrofits.RetrofitApiClient
import com.sellkaro.retrofits.RetrofitService
import com.sellkaro.utils.AppPreference
import com.sellkaro.utils.QuendelTransition
import dmax.dialog.SpotsDialog

import java.util.*
import kotlin.reflect.KClass
import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.graphics.Typeface




abstract class BaseActivity : AppCompatActivity() {

    lateinit var context: Context
    var retrofitClient: RetrofitApiClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        retrofitClient = RetrofitService.retrofit
        context = this
        super.onCreate(savedInstanceState)
    }

    fun proceedToNext(cls: KClass<*>) {
        startActivity(Intent(this, cls.java))
        QuendelTransition.overridePendingTransitionEnter(this)
    }

    fun proceedToNextWithBundel(cls: KClass<*>, bundel: Any) {
        startActivity(Intent(this, cls.java)
                .putExtra(EXTRA_INTENT, bundel as Parcelable))
    }

    fun showHomeBackOnToolbar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        QuendelTransition.overridePendingTransitionExit(this)
    }

    fun hideKeyboard(activity: Activity, view: View?) {
        //  val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun toggle(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive) {
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0) // hide
        } else {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY) // show
        }
    }

    companion object {
        const val EXTRA_INTENT = "getExtra"
        /***
         *  Seter Geter
         * */
        fun setLogin(context: Context) {
            AppPreference.setBooleanPreference(context, Constant.isLogin, true)
        }

        fun getLocale(context: Context): Locale {
            val lang = AppPreference.getStringPreference(context, Constant.LOCAL_LANGUAGE)
            return Locale(lang)
        }

        /**
         *  Alerts....
         * */
        fun showToast(ctx: Context, msg: String) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }

        fun showSnackBar(view: View, msg: String) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show()
        }

        fun showAlert(ctx: Context?, msg: String) {
            ctx?.let {
                AlertDialog.Builder(it)
                        .setTitle(ctx.resources.getString(R.string.alert))
                        .setMessage(msg)
                        .setPositiveButton(ctx.resources.getString(R.string.ok), null)
                        .create()
                        .show()
            }
        }

        fun showAlertWithAction(ctx: Context, title: String, msg: String, cancelable: Boolean,
                                positiveString: String, nagativeString: String,
                                positiveAction: DialogInterface.OnClickListener,
                                negativeAction: DialogInterface.OnClickListener?) {
            AlertDialog.Builder(ctx)
                    .setTitle(title)
                    .setMessage(msg)
                    .setCancelable(cancelable)
                    .setPositiveButton(positiveString, positiveAction)
                    .setNegativeButton(nagativeString, negativeAction)
                    .create()
                    .show()
        }

        fun getDialog(ctx: Context): SpotsDialog {
            val dialog = SpotsDialog(ctx) //R.style.dialog_theme
            dialog.setCancelable(false)
            return dialog
        }

        fun getFont(context: Context):Typeface {
            return Typeface.createFromAsset(context.assets, "Chantelli_Antiqua.ttf")
        }  //txt.setTypeface(font);
    }
} 