package com.aman.cbcassignment.di

import android.content.Context
import com.aman.cbcassignment.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,DatabaseModule::class,ViewModelModule::class])
interface ApplicationComponent {

    //Where are we going to access
    fun inject(mainActivity: MainActivity)

    //Since we need run time value of context to create object of RoomDatabase
    // We have to create Factory
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}