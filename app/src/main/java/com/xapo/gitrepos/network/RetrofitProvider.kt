package com.xapo.gitrepos.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

  fun createRetrofit(): Retrofit {

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .client(client)
        .baseUrl(Urls.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
  }
}