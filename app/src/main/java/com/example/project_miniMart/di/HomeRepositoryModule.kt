package com.example.project_miniMart.di

import com.example.project_miniMart.domain.repositories.HomeRepository
import com.example.project_miniMart.domain.repositories.HomeTask
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {
    @Binds
    abstract fun bindGetHomeRepositoryTask(
        homeRepository: HomeRepository
    ): HomeTask
}