package com.example.newsapp.di

import com.example.data.api.NewsApi
import com.example.data.api.service.NewsApiService
import com.example.data.api.service.NewsApiServiceImpl
import com.example.data.repository.SourceRepositoryImpl
import com.example.domain.repository.SourceRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    }
}