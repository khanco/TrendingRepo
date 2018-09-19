package com.xapo.gitrepos.network

import javax.inject.Inject

class ApiManager @Inject internal constructor(private var service: Service) {
  fun fetchTrendingRepos() = service.fetchTrendingRepos()
}