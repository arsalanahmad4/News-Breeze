package com.example.newsbreeze.dbtest

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsbreeze.db.ArticleDao
import com.example.newsbreeze.db.ArticleDatabase
import com.example.newsbreeze.models.Article
import com.example.newsbreeze.models.Source
import com.example.newsbreeze.testutils.getOrAwaitValue
import com.google.common.truth.ExpectFailure.assertThat
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDatabaseTest  {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: ArticleDatabase
    private lateinit var dao: ArticleDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ArticleDatabase::class.java).allowMainThreadQueries().build()
        dao = db.getArticleDao()
    }

    @After
    fun close() {
        db.close()
    }

    @Test
    fun saveArticle() = runBlocking {
        val article = Article(
            1,
            "Arsalan",
            "Testing Db insertion",
            "Writing instrumented unit test",
            "",
            Source("XYZ","XYZ"),
            "Testing",
            null,
            null
        )
        dao.upsert(article)
        val savedArticles = dao.getAllArticles().getOrAwaitValue()
        Assert.assertEquals(1,savedArticles.size)
        Assert.assertEquals(article, savedArticles[0])
    }


    @Test
    fun deleteArticle() = runBlocking {
        val article = Article(
            1,
            "Arsalan",
            "Testing Db insertion",
            "Writing instrumented unit test",
            "",
            Source("XYZ","XYZ"),
            "Testing",
            null,
            null
        )
        dao.upsert(article)
        val savedArticles = dao.getAllArticles().getOrAwaitValue()
        Assert.assertEquals(1,savedArticles.size)
        dao.deleteArticle(article)
        val savedArticlesAfterDelete = dao.getAllArticles().getOrAwaitValue()
        Assert.assertEquals(0,savedArticlesAfterDelete.size)
    }
}