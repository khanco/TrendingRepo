package com.xapo.gitrepos.screens.trendingrepos.dagger

import com.xapo.gitrepos.dagger.components.ApiManagerComponent
import com.xapo.gitrepos.screens.trendingrepos.mvvm.views.TrendingGitRepoListActivity
import dagger.Component

@Component (dependencies = [ApiManagerComponent::class], modules = [RepoListModule::class])
interface RepoListComponent {
  fun inject(activity: TrendingGitRepoListActivity)
}