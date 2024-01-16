package com.muazdev26.composenewsapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertBookMark(articleEntity: ArticleEntity)

    @Delete
    suspend fun deleteArticle(articleEntity: ArticleEntity)

    @Query("SELECT * FROM articleentity")
    fun getAllArticles(): Flow<List<ArticleEntity>?>

    @Query("SELECT * FROM articleentity WHERE url=:url")
    fun getArticle(url: String): ArticleEntity?

}