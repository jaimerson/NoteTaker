package com.example.notetaker

import android.app.Application
import android.content.Context

class MyApplication : Application() {
    companion object {
        var context: Context? = null

        fun getApplicationContext(): Context? {
            return this.context
        }
    }

    override fun onCreate() {
        super.onCreate()
        MyApplication.context = applicationContext
    }
}