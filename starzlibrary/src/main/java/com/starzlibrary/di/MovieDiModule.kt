package com.starzlibrary.di

import BASE_URL
import com.google.gson.GsonBuilder
import com.starzlibrary.data.remote.MovieApiDefinition
import com.starzlibrary.data.repositoiresImpl.MovieSearchRepositoryImp
import com.starzlibrary.domain.repositories.MovieSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDiModule {

    private const val HTTP_REQUEST_TIMEOUT = 2L

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().followRedirects(false)
            .callTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .connectTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)
            .writeTimeout(HTTP_REQUEST_TIMEOUT, TimeUnit.MINUTES)

        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        clientBuilder.addInterceptor(intercepter)

        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val gson = GsonBuilder()
            .create()
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL).build()
    }

    @Singleton
    @Provides
    fun provideMovieApiDefinition(retrofit: Retrofit): MovieApiDefinition {
        return retrofit.create(MovieApiDefinition::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieSearchRepository(api: MovieApiDefinition): MovieSearchRepository {
        return MovieSearchRepositoryImp(api)
    }


}