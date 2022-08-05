package com.example.greenlighassignment.remote

import com.example.greenlighassignment.model.ModelData
import com.example.greenlighassignment.utils.Constant
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.END_POINT)
    suspend fun  callApi(
    ) : Response<ModelData>
}