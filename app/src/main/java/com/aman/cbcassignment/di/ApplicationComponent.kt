package com.aman.cbcassignment.di

import com.aman.cbcassignment.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ApplicationComponent {

    //Where are we going to access
    fun inject(mainActivity: MainActivity)
}