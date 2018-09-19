package com.xapo.gitrepos.dagger.modules

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.xapo.gitrepos.network.Service
import com.xapo.gitrepos.network.Urls
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiMangerModule {

  @Provides
  fun providesService(retrofit: Retrofit): Service {
    return retrofit.create(Service::class.java)
  }

  @Provides
  fun provideRetrofit(): Retrofit {

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