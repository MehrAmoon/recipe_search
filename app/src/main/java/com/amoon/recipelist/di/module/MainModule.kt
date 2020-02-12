package com.amoon.recipelist.di.module

import androidx.lifecycle.ViewModel
import com.amoon.recipelist.di.key.ViewModelKey
import com.amoon.recipelist.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindsViewModel(viewModel: MainViewModel): ViewModel
}
