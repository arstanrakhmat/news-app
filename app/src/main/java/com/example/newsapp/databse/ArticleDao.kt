package com.example.newsapp.databse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.models.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticle(): LiveData<List<Article>>

    @Query("SELECT * FROM articles WHERE title LIKE :title AND isArchived LIKE :isArchived")
    fun getSearchArchiveArticle(title: String, isArchived: Boolean = true): LiveData<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)
}