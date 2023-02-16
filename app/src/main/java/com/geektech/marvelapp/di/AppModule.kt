package com.geektech.marvelapp.di


import android.content.Context
import androidx.room.Room
import com.geektech.marvelapp.BuildConfig
import com.geektech.marvelapp.data.local.room.AppDataBase
import com.geektech.marvelapp.data.local.room.ComicDao
import com.geektech.marvelapp.data.remote.ImdbApi
import com.geektech.marvelapp.data.remote.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun imbdApiProvide(): ImdbApi {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_IMDB)
            .addConverterFactory(GsonConverterFactory.create()).build().create(ImdbApi::class.java)
    }

    @Provides
    fun marvelApiProvide():MarvelApi{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_MARVEL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(MarvelApi::class.java)
    }

    @Provides
    fun comicDaoProvide(appDataBase: AppDataBase): ComicDao {
        return appDataBase.getDao()
    }

    @Provides
    fun appDataBaseProvide(@ApplicationContext applicationContext: Context): AppDataBase {
        return Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java,
            "comic"
        ).allowMainThreadQueries().build()
    }
}