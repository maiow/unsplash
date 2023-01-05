package com.example.unsplashattestation.di

import com.example.unsplashattestation.data.repository.*
import com.example.unsplashattestation.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindPhotoRemoteRepository(
        photoRemoteRepository: PhotoRemoteRepositoryImpl
    ): PhotoRemoteRepository

    @Singleton
    @Binds
    abstract fun bindLocalRepository(
        localRepository: LocalRepositoryImpl
    ): LocalRepository

    @Singleton
    @Binds
    abstract fun bindDigestRemoteRepository(
        digestRemoteRepository: DigestRemoteRepositoryImpl
    ): DigestRemoteRepository

    @Singleton
    @Binds
    abstract fun bindProfileRemoteRepository(
        profileRemoteRepository: ProfileRemoteRepositoryImpl
    ): ProfileRemoteRepository

    @Singleton
    @Binds
    abstract fun bindPhotosPagingSourceRepository(
        photosPagingSourceRepository: PhotosPagingSourceRepositoryImpl
    ): PhotosPagingSourceRepository
}