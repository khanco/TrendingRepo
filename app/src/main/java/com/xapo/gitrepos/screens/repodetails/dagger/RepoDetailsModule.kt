package com.xapo.gitrepos.screens.repodetails.dagger

import com.xapo.gitrepos.screens.repodetails.mvvm.viewmodels.RepoDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class RepoDetailsModule {
  @Provides
  fun provideRepoDetailsViewModel() = RepoDetailsViewModel()
}