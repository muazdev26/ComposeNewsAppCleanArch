package com.muazdev26.composenewsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(exportSchema = true, entities = [ArticleEntity::class], version = 1)
@TypeConverters(SourceTypeConvertor::class)
abstract class NewsDatabase : RoomDatabase(){

    abstract val newsDao: NewsDao
}