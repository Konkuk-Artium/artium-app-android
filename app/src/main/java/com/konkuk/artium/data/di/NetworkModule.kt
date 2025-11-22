package com.konkuk.artium.data.di

import com.konkuk.artium.BuildConfig
import com.konkuk.artium.data.api.ArchiveService
import com.konkuk.artium.data.api.CommunityService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @LoginNetwork
    @Provides
    @Singleton
    fun provideLoginRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LOGIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @DataNetwork
    @Provides
    @Singleton
    fun provideDataRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DATA_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideArchiveService(
        @DataNetwork retrofit: Retrofit
    ): ArchiveService =
        retrofit.create(ArchiveService::class.java)

    @Provides
    @Singleton
    fun provideCommunityService(
        @DataNetwork retrofit: Retrofit
    ): CommunityService =
        retrofit.create(CommunityService::class.java)
}
