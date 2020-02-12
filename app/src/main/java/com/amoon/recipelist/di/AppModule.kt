package com.amoon.recipelist.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.amoon.recipelist.BaseViewModelFactory
import com.amoon.recipelist.data.repository.AppDataRepository
import com.amoon.recipelist.data.repository.AppRepository
import com.amoon.recipelist.di.qualifier.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module(includes = [AppModule.ProvideModule::class])
interface AppModule {
    @Module
    class ProvideModule {

        @Singleton
        @Provides
        @App
        fun provideContext(): Context = com.amoon.recipelist.App.instance

        @Provides
        @Singleton
        fun provideCompositeDisposable(): CompositeDisposable {
            return CompositeDisposable()
        }
    }

    @Binds
    @Singleton
    fun bindsAppRepository(repository: AppDataRepository): AppRepository


    @Binds
    fun bindsViewModelFactory(viewModelFactory: BaseViewModelFactory): ViewModelProvider.Factory
}