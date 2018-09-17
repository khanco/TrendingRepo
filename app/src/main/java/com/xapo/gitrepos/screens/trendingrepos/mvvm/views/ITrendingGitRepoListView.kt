package com.xapo.gitrepos.screens.trendingrepos.mvvm.views

import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel

interface ITrendingGitRepoListView {
  fun showTrendingList(response: List<RepositoryDetailsModel>)
  fun showErrorMessage()
  fun showLoader()
  fun hideLoader()
}