package com.sermedkerim.bitirmeprojesi.di

import android.content.Context
import com.sermedkerim.bitirmeprojesi.AppPref
import com.sermedkerim.bitirmeprojesi.MainActivity
import com.sermedkerim.bitirmeprojesi.data.datasource.FoodDataSource
import com.sermedkerim.bitirmeprojesi.data.entity.Food
import com.sermedkerim.bitirmeprojesi.data.repo.FoodRepository
import com.sermedkerim.bitirmeprojesi.retrofit.ApiUtils
import com.sermedkerim.bitirmeprojesi.retrofit.FoodDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideFoodDataSource(foodDao: FoodDao,appPref: AppPref):FoodDataSource{
        return FoodDataSource(foodDao,appPref)
    }

    @Provides
    @Singleton
    fun provideFoodDao():FoodDao{
        return ApiUtils.getFoodDao()
    }

    @Provides
    @Singleton
    fun provideAppPref(@ApplicationContext context:Context):AppPref{
        return AppPref(context)
    }
}