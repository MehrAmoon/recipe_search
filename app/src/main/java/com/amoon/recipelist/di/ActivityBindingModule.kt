package com.amoon.recipelist.di

import com.amoon.recipelist.ui.MainActivity
import com.amoon.recipelist.di.module.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun mainActivity(): MainActivity
}
