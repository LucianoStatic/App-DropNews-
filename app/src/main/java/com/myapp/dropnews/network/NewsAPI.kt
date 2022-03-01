package com.myapp.dropnews.network

import com.myapp.dropnews.model.NewsResponse
import com.myapp.dropnews.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    //END POINTS

    @GET("/v2/top-headlines?")
    suspend fun getBreakingNews(

        @Query("country")
        contryCode: String = "br",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>


    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apikey")
        apiKey: String = Constants.API_KEY

    ):Response<NewsResponse>

}