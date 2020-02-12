package com.amoon.recipelist

import android.app.Activity
import android.app.Application
import com.amoon.recipelist.di.AppComponent
import com.amoon.recipelist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    private var appComponent: AppComponent? = null

    companion object {
        lateinit var instance: App
            private set
    }

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
    override fun onCreate() {
        super.onCreate()
        instance = this

        setupDagger()
    }


    private fun setupDagger() {
        DaggerAppComponent
            .builder()
            .create(this)
            .inject(this)
    }

    fun setAppComponent(appComponent: AppComponent) {
        this.appComponent = appComponent
    }
}