package com.example.project_miniMart.di

import com.example.project_miniMart.domain.repositories.LoginRepository
import com.example.project_miniMart.domain.repositories.LoginTask
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginRepositoryModule {
    @Binds
    abstract fun bindGetLoginRepositoryTask(
        loginRepository: LoginRepository
    ): LoginTask
}