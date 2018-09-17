package com.xapo.gitrepos.network

import retrofit2.Retrofit

class ApiManager {

  private var retrofit: Retrofit? = null
  private var service: Service? = null

  init {
    if (retrofit == null) {
      retrofit = RetrofitProvider().createRetrofit()
      service = retrofit!!.create(Service::class.java)
    }
  }

  fun fetchTrendingRepos() = service!!.fetchTrendingRepos()
}