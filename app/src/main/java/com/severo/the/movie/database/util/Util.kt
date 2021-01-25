package com.severo.the.movie.database.util

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.Html
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.severo.the.movie.database.GiraChallengeApplication
import com.severo.the.movie.database.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object Util {

    private val sessionPreferences: SharedPreferences?
        private get() {
            val ctx: Context? = GiraChallengeApplication.instance
            return ctx?.getSharedPreferences("SESSION_PREFERENCES", Context.MODE_PRIVATE)
        }

    var apiToken: String?
        get() = sessionPreferences?.getString("API_TOKEN", null)
        set(token) {
            val mPreferences = sessionPreferences
            val editor = mPreferences?.edit()
            editor?.putString("API_TOKEN", token)
            editor?.apply()
        }

    @JvmStatic
    fun loadingDialog(ctx: Context?): Dialog {
        val loading = Dialog(ctx!!)
        loading.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loading.setContentView(R.layout.dialog_loading)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Objects.requireNonNull(loading.window)!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        loading.setCanceledOnTouchOutside(false)
        loading.setCancelable(false)
        return loading
    }

    @JvmStatic
    fun alert(
        ctx: Context?,
        title: String?,
        message: String?
    ) {
        val alertDialogBuilder =
            android.app.AlertDialog.Builder(ctx)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(Html.fromHtml(message))
        alertDialog.show()
    }

    @JvmStatic
    fun loadImage(context: Context?, uri: String?, imageView: ImageView) {
        val options = RequestOptions()
        options.error(R.drawable.no_image_movie)
        Glide.with(context!!)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(imageView!!)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDateFormat(date: String?, initDateFormat: String?, endDateFormat: String?): String {
        return try {
            val initDate = SimpleDateFormat(initDateFormat).parse(date)
            val formatter = SimpleDateFormat(endDateFormat)
            formatter.format(initDate)
        } catch (e: ParseException) {
            e.printStackTrace()
            "Erro ao obter data"
        }
    }


}