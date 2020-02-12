package com.amoon.recipelist.di.module


import com.amoon.recipelist.ui.detail.DetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeGitDetailFragment(): DetailFragment
}
