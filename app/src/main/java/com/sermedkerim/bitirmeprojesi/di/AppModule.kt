package com.sermedkerim.bitirmeprojesi.di

import com.sermedkerim.bitirmeprojesi.data.datasource.FoodDataSource
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import com.sermedkerim.bitirmeprojesi.retrofit.ApiUtils
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodRepository(foodDataSource: FoodDataSource):FoodRepository{
        return FoodRepository(foodDataSource)
    }

    @Provides
    @Singleton
    fun provideFoodDataSource(foodDao: FoodDao):FoodDataSource{
        return FoodDataSource(foodDao)
    }

    @Provides
    @Singleton
    fun provideFoodDao():FoodDao{
        return ApiUtils.getFoodDao()
    }
}