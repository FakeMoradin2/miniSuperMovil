package com.example.project_miniMart.di

import android.content.Context
import androidx.room.Room
import com.example.project_miniMart.datasource.local.bd.MiniMartDataBase
import com.example.project_miniMart.utils.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModuleHilt {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): MiniMartDataBase {
        return Room.databaseBuilder(context, MiniMartDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}