package com.muazdev26.composenewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(SourceTypeConvertor::class)
@Database(exportSchema = false, entities = [ArticleEntity::class], version = 1)
abstract class NewsDatabase : RoomDatabase(){

    abstract val newsDao: NewsDao
}