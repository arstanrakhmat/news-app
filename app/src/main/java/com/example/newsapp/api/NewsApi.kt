package com.example.newsapp.api

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * This interface will be used to define our single request that we can execute form code
 */

interface NewsApi {

    //get all the Breaking news from API ---- v2/top-headlines according to documentation
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(

        //Specify from which country we want to get news
        //If it is request parameter, annotate with request parameter
        @Query("country")
        countryCode: String = "us",

        @Query("page")
        pageNumber: Int = 1,

        //We need to include api key, so the newsAPI will know who make the request
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    //search request from API ---- v2/everything according to documentation
    @GET("v2/everything")
    suspend fun searchForNews(

        //q - query. Search for particular string
        @Query("q")
        searchQuery: String,

        @Query("page")
        pageNumber: Int = 1,

        //We need to include api key, so the newsAPI will know who make the request
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>
}