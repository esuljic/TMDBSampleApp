package com.stage.imdb.data.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.stage.imdb.app.Config.BASE_URL
import com.stage.imdb.data.response.MediaItemsResponse
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("trending/movies/day?api_key=cc26706ac345e4ec80e52cd496ed510c")
    fun getMoviesAsync(): Deferred<MediaItemsResponse>

    @GET("trending/tv/day?api_key=cc26706ac345e4ec80e52cd496ed510c")
    fun getTVShowsAsync(): Deferred<MediaItemsResponse>

    @GET("search/movie?api_key=cc26706ac345e4ec80e52cd496ed510c")
    fun findMoviesAsync(
        @Query("query") query: String
    ): Deferred<MediaItemsResponse>

    @GET("search/tv?api_key=cc26706ac345e4ec80e52cd496ed510c")
    fun findTVShowsAsync(
        @Query("query") query: String
    ): Deferred<MediaItemsResponse>

    companion object {
        operator fun invoke(): API {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder().setLenient()
                .create()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(API::class.java)
        }
    }
}