package com.aman.cbcassignment

import android.app.Application
import com.aman.cbcassignment.di.ApplicationComponent
import com.aman.cbcassignment.di.DaggerApplicationComponent

class NewsApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}