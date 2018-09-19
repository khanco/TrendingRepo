package com.xapo.gitrepos.screens.trendingrepos.dagger

import com.xapo.gitrepos.network.ApiManager
import com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels.TrendingRepoListingViewModel
import dagger.Module
import dagger.Provides

@Module
class RepoListModule {
  @Provides
  fun provideViewModel(apiManager: ApiManager) = TrendingRepoListingViewModel(apiManager)
}