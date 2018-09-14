package com.xapo.gitrepos.screens.trendingrepos.mvp.views

import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails

interface ITrendingGitRepoListView {
  fun showTrendingList(response: List<RepositoryDetails>)
  fun showErrorMessage()
  fun showLoader()
  fun hideLoader()
}