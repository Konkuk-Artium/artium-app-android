package com.konkuk.artium.data.di

import com.konkuk.artium.data.repository.ArchiveRepository
import com.konkuk.artium.data.repository.CommunityRepository
import com.konkuk.artium.data.repositoryImpl.ArchiveRepositoryImpl
import com.konkuk.artium.data.repositoryImpl.CommunityRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindArchiveRepository(
        impl: ArchiveRepositoryImpl
    ): ArchiveRepository


    @Binds
    abstract fun bindCommunityRepository(
        impl: CommunityRepositoryImpl
    ): CommunityRepository

}