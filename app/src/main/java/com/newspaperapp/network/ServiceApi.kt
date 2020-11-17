package com.newspaperapp.network

import com.newspaperapp.dataModel.MainData
import retrofit2.http.GET

interface ServiceApi {
    @GET("data1.json")
    suspend fun getArticlesCategory(): MainData
}