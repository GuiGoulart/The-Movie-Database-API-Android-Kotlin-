package com.severo.the.movie.database

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho

class GiraChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this@GiraChallengeApplication)

        instance = applicationContext
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        var instance: Context? = null
            private set
    }

}