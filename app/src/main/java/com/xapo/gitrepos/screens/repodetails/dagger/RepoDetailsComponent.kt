package com.xapo.gitrepos.screens.repodetails.dagger

import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import dagger.Component

@Component (modules = [RepoDetailsModule::class])
interface RepoDetailsComponent {
  fun inject(activity: RepoDetailsActivity)
}