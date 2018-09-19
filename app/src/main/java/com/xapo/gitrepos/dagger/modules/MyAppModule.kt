package com.xapo.gitrepos.dagger.modules

import android.app.Application
import com.xapo.gitrepos.network.ApiManager
import com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels.TrendingRepoListingViewModel
import com.xapo.gitrepos.utils.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class MyAppModule(private val app: Application) {
  @Provides
  fun provideMyApp() = app

//  @Provides
//  internal fun provideTrendingRepoViewModel(apiManager: ApiManager) = TrendingRepoListingViewModel(apiManager)
}