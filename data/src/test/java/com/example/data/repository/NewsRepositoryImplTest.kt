package com.example.data.repository

import com.example.data.api.service.NewsApiService
import com.example.data.db.dao.ArticleDao
import com.example.data.db.dao.SourceDao
import com.example.data.db.entity.ArticleEntity
import com.example.data.db.entity.SourceEntity
import com.example.data.model.ArticleApiModel
import com.example.data.model.SourceApiModel
import com.example.domain.model.Article
import com.example.domain.model.Source
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryImplTest {

    private val newsApiService = mockk<NewsApiService>()
    private val sourceDao = mockk<SourceDao>()
    private val articleDao = mockk<ArticleDao>()

    private lateinit var newsRepositoryImpl: NewsRepositoryImpl

    @Before
    fun setup() {
        newsRepositoryImpl = NewsRepositoryImpl(newsApiService, sourceDao, articleDao)
    }

    @Test
    fun `Given getSources returns empty list, when getArticles called, then return empty list`() =
        runTest {
            every { sourceDao.getSources() } returns flowOf(emptyList())

            val result = newsRepositoryImpl.getArticles()

            Assert.assertEquals(emptyList<Article>(), result)
        }

    @Test
    fun `Given getSources returns non-empty list, when getArticles called, then call getArticles on NewsApiService with comma separated sources list`() =
        runTest {
            val sources = listOf(SourceEntity("nine-news"), SourceEntity("seven-news"))

            every { sourceDao.getSources() } returns flowOf(sources)
            coEvery { newsApiService.getArticles("nine-news, seven-news") } returns emptyList()

            newsRepositoryImpl.getArticles()

            coVerify { newsApiService.getArticles("nine-news, seven-news") }
        }

    @Test
    fun `Given getSources returns non-empty list and NewsApiService_getArticles returns non-empty list, when getArticles called, then return articles list`() =
        runTest {
            val sources = listOf(SourceEntity("nine-news"), SourceEntity("seven-news"))
            val articles = listOf(
                ArticleApiModel(
                    author = "author1",
                    title = "title1",
                    description = "description1",
                    articleUrl = "articleUrl1",
                    articleImageUrl = "articleImageUrl1"
                ),
                ArticleApiModel(
                    author = "author2",
                    title = "title2",
                    description = "description2",
                    articleUrl = "articleUrl2",
                    articleImageUrl = "articleImageUrl2"
                )
            )

            every { sourceDao.getSources() } returns flowOf(sources)
            coEvery { newsApiService.getArticles("nine-news, seven-news") } returns articles

            val result = newsRepositoryImpl.getArticles()
            val expectedArticles = listOf(
                Article(
                    author = "author1",
                    title = "title1",
                    description = "description1",
                    articleUrl = "articleUrl1",
                    articleImageUrl = "articleImageUrl1"
                ),
                Article(
                    author = "author2",
                    title = "title2",
                    description = "description2",
                    articleUrl = "articleUrl2",
                    articleImageUrl = "articleImageUrl2"
                ),
            )

            Assert.assertEquals(expectedArticles, result)
        }

    @Test
    fun `Given ArticlesDao_getArticles returns articles, when getSavedArticles called, then return articles`() =
        runTest {
            val savedArticles = listOf(
                ArticleEntity(
                    url = "url",
                    imageUrl = "imageUrl",
                    author = "author",
                    title = "title",
                    description = "description"
                )
            )
            val expectedArticlesList = listOf(
                Article(
                    author = "author",
                    title = "title",
                    description = "description",
                    articleUrl = "url",
                    articleImageUrl = "imageUrl",
                )
            )

            every { articleDao.getArticles() } returns flowOf(savedArticles)

            val result = newsRepositoryImpl.getSavedArticles().firstOrNull()

            Assert.assertEquals(expectedArticlesList, result)

        }

    @Test
    fun `When addArticleToDb called, then call addArticle on ArticlesDao`() = runTest {
        val article = Article(
            author = "author",
            title = "title",
            description = "description",
            articleUrl = "url",
            articleImageUrl = "imageUrl",
        )
        val articleEntity = ArticleEntity(
            url = "url",
            imageUrl = "imageUrl",
            author = "author",
            title = "title",
            description = "description",
        )

        coEvery { articleDao.addArticle(articleEntity) } just Runs

        newsRepositoryImpl.addArticleToDb(article)

        coVerify { articleDao.addArticle(articleEntity) }
    }

    @Test
    fun `When removeArticleToDb called, then call removeArticle on ArticlesDao`() = runTest {
        val article = Article(
            author = "author",
            title = "title",
            description = "description",
            articleUrl = "url",
            articleImageUrl = "imageUrl",
        )
        val articleEntity = ArticleEntity(
            url = "url",
            imageUrl = "imageUrl",
            author = "author",
            title = "title",
            description = "description",
        )

        coEvery { articleDao.removeArticle(articleEntity) } just Runs

        newsRepositoryImpl.removeArticleFromDb(article)

        coVerify { articleDao.removeArticle(articleEntity) }
    }

    @Test
    fun `Given NewsApiService_getSources and SourcesDao_getSources returns sources, when getSources called, then return the combined list`() =
        runTest {
            val sourceApiModels = listOf(
                SourceApiModel(
                    id = "id1",
                    name = "name1"
                ), SourceApiModel(
                    id = "id2",
                    name = "name2"
                )
            )
            val sourceDbModels = listOf(
                SourceEntity(sourceId = "id2")
            )

            val expectedSources = listOf(
                Source(
                    id = "id1",
                    name = "name1",
                    isSelected = false
                ),
                Source(
                    id = "id2",
                    name = "name2",
                    isSelected = true
                )
            )

            coEvery { newsApiService.getSources() } returns sourceApiModels
            every { sourceDao.getSources() } returns flowOf(sourceDbModels)

            val result = newsRepositoryImpl.getSources().firstOrNull()

            Assert.assertEquals(expectedSources, result)
        }

    @Test
    fun `When addSourceToDb called, then call addSource on SourceDao`() = runTest {
        val sourceEntity = SourceEntity("id")
        coEvery { sourceDao.addSource(sourceEntity) } just Runs

        newsRepositoryImpl.addSourceToDb("id")

        coVerify { sourceDao.addSource(sourceEntity) }
    }

    @Test
    fun `When removeSourceToDb called, then call removeSource on SourceDao`() = runTest {
        val sourceEntity = SourceEntity("id")
        coEvery { sourceDao.removeSource(sourceEntity) } just Runs

        newsRepositoryImpl.removeSourceFromDb("id")

        coVerify { sourceDao.removeSource(sourceEntity) }
    }

    @Test
    fun `Given ArticleDao_articleExist returns true, when isArticleFavourite called, then return true`() =
        runTest {
            val article = Article(
                author = "author",
                title = "title",
                description = "description",
                articleUrl = "articleUrl",
                articleImageUrl = "imageUrl"
            )
            every { articleDao.articleExist("articleUrl") } returns flowOf(true)

            val result = newsRepositoryImpl.isArticleFavourite(article).firstOrNull()

            verify { articleDao.articleExist("articleUrl") }
            Assert.assertEquals(true, result)
        }
}