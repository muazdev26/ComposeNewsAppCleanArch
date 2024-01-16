package com.muazdev26.composenewsapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.muazdev26.composenewsapp.domain.models.Source

@Entity
data class ArticleEntity(
    @PrimaryKey
    val url: String,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val urlToImage: String
)