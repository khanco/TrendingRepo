package com.xapo.gitrepos.network

import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails
import io.reactivex.Observable
import retrofit2.http.GET

interface Service {

  @GET(Urls.TRENDING_REPOS)
  fun fetchTrendingRepos(): Observable<List<RepositoryDetails>>
}