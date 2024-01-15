package com.muazdev26.composenewsapp.di

import android.app.Application
import androidx.room.Room
import com.muazdev26.composenewsapp.data.local.NewsDao
import com.muazdev26.composenewsapp.data.local.NewsDatabase
import com.muazdev26.composenewsapp.data.local.SourceTypeConvertor
import com.muazdev26.composenewsapp.data.prefrences.AppPreferencesHelperImpl
import com.muazdev26.composenewsapp.data.remote.NewsApi
import com.muazdev26.composenewsapp.data.remote.repository.NewsRepositoryImpl
import com.muazdev26.composenewsapp.domain.prefrences.AppPreferencesHelper
import com.muazdev26.composenewsapp.domain.repositories.NewsRepository
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.BookMarkUseCases
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.DeleteBookMarkUseCase
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.GetBookMarksUseCase
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.GetSingleBookMarkUseCase
import com.muazdev26.composenewsapp.domain.usecases.bookmarks.UpsertBookMarkUseCase
import com.muazdev26.composenewsapp.domain.usecases.home.GetNewsUseCase
import com.muazdev26.composenewsapp.domain.usecases.home.NewsUseCases
import com.muazdev26.composenewsapp.domain.usecases.launch.AppEntryUseCases
import com.muazdev26.composenewsapp.domain.usecases.launch.ReadAppEntryUseCase
import com.muazdev26.composenewsapp.domain.usecases.launch.SaveAppEntryUseCase
import com.muazdev26.composenewsapp.domain.usecases.search.SearchUseCase
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
        newsApi: NewsApi, newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(
        newsApi, newsDao
    )

    @Provides
    @Singleton
    fun providesNewsUseCases(
        newsRepository: NewsRepository
    ) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository)
    )

    @Provides
    @Singleton
    fun providesBookmarksUseCases(
        newsRepository: NewsRepository
    ) = BookMarkUseCases(
        getBookMarksUseCase = GetBookMarksUseCase(newsRepository),
        getSingleBookMarkUseCase = GetSingleBookMarkUseCase(newsRepository),
        deleteBookMarkUseCase = DeleteBookMarkUseCase(newsRepository),
        upsertBookMarkUseCase = UpsertBookMarkUseCase(newsRepository),
    )

    @Provides
    @Singleton
    fun providesSearchUseCases(
        newsRepository: NewsRepository
    ) = SearchUseCase(
        newsRepository
    )

    @Provides
    @Singleton
    fun providesNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(application, NewsDatabase::class.java, "news_db.db")
            .addTypeConverter(SourceTypeConvertor::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsSourcesDao(
        newsDatabase: NewsDatabase
    ): NewsDao {
        return newsDatabase.newsDao
    }


}