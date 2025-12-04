package com.example.project_miniMart.di

import com.example.project_miniMart.datasource.local.bd.MiniMartDataBase
import com.example.project_miniMart.datasource.local.bd.dao.SaleDao
import com.example.project_miniMart.datasource.local.bd.dao.ShoppingDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataBaseModuleHilt::class])
@InstallIn(SingletonComponent::class)
object ModuleDAOHilt {

    @Provides
    @Singleton
    fun provideDAO(shoppingDataBase: MiniMartDataBase): ShoppingDAO {
        return shoppingDataBase.shoppingDAO()
    }

    @Provides
    @Singleton
    fun provideSaleDAO(shoppingDataBase: MiniMartDataBase): SaleDao {
        return shoppingDataBase.saleDAO()
    }
}