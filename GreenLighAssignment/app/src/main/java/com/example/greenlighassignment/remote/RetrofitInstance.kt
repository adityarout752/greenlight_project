package com.example.greenlighassignment.remote

import com.example.greenlighassignment.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
   companion object{
       private val retrofit by lazy{
           val logging=HttpLoggingInterceptor()
           logging.setLevel(HttpLoggingInterceptor.Level.BODY)
           val client=OkHttpClient.Builder()
               .addInterceptor(logging)
               .build()
           Retrofit.Builder()
               .baseUrl(Constant.BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())//how response be converted and interpreted to kotlin object
               .client(client).build()
       }

       val api by lazy{
           retrofit.create(ApiService::class.java)
       }
   }

}