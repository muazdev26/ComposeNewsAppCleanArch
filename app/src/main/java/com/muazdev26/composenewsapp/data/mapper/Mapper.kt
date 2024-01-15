package com.muazdev26.composenewsapp.data.mapper

import com.muazdev26.composenewsapp.data.local.ArticleEntity
import com.muazdev26.composenewsapp.domain.models.Article

fun ArticleEntity.toArticle() = Article(
    author, content, description, publishedAt, source, title, url, urlToImage, id
)

fun Article.toArticleEntity() = ArticleEntity(
    author = author ?: "",
    content = content,
    description = description,
    publishedAt = publishedAt,
    source = source,
    title = title,
    url = url,
    urlToImage = urlToImage
)