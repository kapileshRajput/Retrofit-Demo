package com.example.kapilesh.retrofitdemo.network

import com.example.kapilesh.retrofitdemo.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    //repositories?q=newyork
    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): retrofit2.Call<RecyclerList>
}