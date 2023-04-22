package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.data.api.NewsApi
import com.example.data.api.service.NewsApiService
import com.example.data.api.service.NewsApiServiceImpl
import com.example.data.db.AppDatabase
import com.example.data.db.dao.SourceDao
import com.example.data.repository.SourceRepositoryImpl
import com.example.domain.repository.SourceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    abstract fun provideSourceRepository(sourceRepositoryImpl: SourceRepositoryImpl): SourceRepository

    @Binds
    abstract fun provideNewsApiService(newsApiServiceImpl: NewsApiServiceImpl): NewsApiService

    companion object {

        @Provides
        fun provideRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Provides
        fun provideNewsApi(retrofit: Retrofit): NewsApi {
            return retrofit.create(NewsApi::class.java)
        }

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext applicationContext: Context)
                : AppDatabase {
            return Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "app-database"
            ).build()
        }

        @Provides
        fun provideSourceDao(appDatabase: AppDatabase): SourceDao {
            return appDatabase.sourceDao()
        }
    }
}