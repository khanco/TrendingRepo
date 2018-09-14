package com.xapo.gitrepos.network

import retrofit2.Retrofit

class ApiManager {

  private val retrofit: Retrofit = RetrofitProvider().createRetrofit()
  private val service: Service = retrofit.create(Service::class.java)

  fun fetchTrendingRepos() = service.fetchTrendingRepos()
}