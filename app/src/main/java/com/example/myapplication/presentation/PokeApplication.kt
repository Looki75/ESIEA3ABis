package com.example.myapplication.presentation

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class PokeApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}