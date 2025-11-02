package com.example.minimartapp.di

import com.example.minimartapp.domain.repositories.LoginRepository
import com.example.minimartapp.domain.repositories.LoginTask
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