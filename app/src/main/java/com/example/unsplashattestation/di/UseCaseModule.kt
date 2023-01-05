package com.example.unsplashattestation.di

import com.example.unsplashattestation.data.usecase.*
import com.example.unsplashattestation.domain.usecase.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindDigestPagingUseCase(
        digestPagingUseCase: DigestPagingUseCaseImpl
    ): DigestPagingUseCase

    @Singleton
    @Binds
    abstract fun bindGetDigestInfoUseCase(
        getDigestInfoUseCase: GetDigestInfoUseCaseImpl
    ): GetDigestInfoUseCase

    @Singleton
    @Binds
    abstract fun bindGetProfileUseCase(
        getProfileUseCase: GetProfileUseCaseImpl
    ): GetProfileUseCase

    @Singleton
    @Binds
    abstract fun bindLikeDetailPhotoUseCase(
        likeDetailPhotoUseCase: LikeDetailPhotoUseCaseImpl
    ): LikeDetailPhotoUseCase

    @Singleton
    @Binds
    abstract fun bindOnePhotoDetailsUseCase(
        onePhotoDetailsUseCase: OnePhotoDetailsUseCaseImpl
    ): OnePhotoDetailsUseCase

    @Singleton
    @Binds
    abstract fun bindPhotoLikeUseCase(
        photoLikeUseCase: PhotoLikeUseCaseImpl
    ): PhotoLikeUseCase

    @Singleton
    @Binds
    abstract fun bindPhotosPagingUseCase(
        photosPagingUseCase: PhotosPagingUseCaseImpl
    ): PhotosPagingUseCase


}