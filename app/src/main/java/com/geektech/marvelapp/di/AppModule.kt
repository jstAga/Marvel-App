package com.geektech.marvelapp.di


import com.geektech.marvelapp.BuildConfig
import com.geektech.marvelapp.data.remote.ImbdApi
import com.geektech.marvelapp.data.remote.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun imbdApiProvide(): ImbdApi {
        return Retrofit.Builder().baseUrl("https://imdb8.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(ImbdApi::class.java)
    }

    @Provides
    fun marvelApiProvide():MarvelApi{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL_MARVEL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(MarvelApi::class.java)
    }
}