package com.muazdev26.composenewsapp.di

import android.app.Application
import com.muazdev26.composenewsapp.data.prefrences.AppPreferencesHelperImpl
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.data.remote.repository.NewsRepositoryImpl
import com.muazdev26.composenewsapp.domain.prefrences.AppPreferencesHelper
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository
import com.muazdev26.composenewsapp.domain.usecases.home.GetNewsUseCase
import com.muazdev26.composenewsapp.domain.usecases.home.NewsUseCases
import com.muazdev26.composenewsapp.domain.usecases.launch.AppEntryUseCases
import com.muazdev26.composenewsapp.domain.usecases.launch.ReadAppEntryUseCase
import com.muazdev26.composenewsapp.domain.usecases.launch.SaveAppEntryUseCase
import com.muazdev26.composenewsapp.util.Consts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun providesAppPrefHelper(
        application: Application
    ): AppPreferencesHelper = AppPreferencesHelperImpl(application)

    @Provides
    @Singleton
    fun providesAppEntryUseCases(
        appPreferencesHelper: AppPreferencesHelper
    ) = AppEntryUseCases(
        saveAppEntryUseCase = SaveAppEntryUseCase(appPreferencesHelper),
        readAppEntryUseCase = ReadAppEntryUseCase(appPreferencesHelper)
    )


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Consts.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesNewsApi(
        retrofit: Retrofit
    ): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(
        newsApi
    )

    @Provides
    @Singleton
    fun providesNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository)
    )
}