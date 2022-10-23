package com.aman.cbcassignment

import android.app.Application
import com.aman.cbcassignment.di.ApplicationComponent
import com.aman.cbcassignment.di.DaggerApplicationComponent
import com.aman.cbcassignment.utils.NetworkUtils

class NewsApplication : Application() {

    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
        NetworkUtils.setNetworkObserver(this)
    }
}