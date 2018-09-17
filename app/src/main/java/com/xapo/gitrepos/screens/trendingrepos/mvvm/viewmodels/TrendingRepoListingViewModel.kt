package com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels

import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.network.ApiManager
import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import com.xapo.gitrepos.screens.trendingrepos.mvvm.views.ITrendingGitRepoListView
import com.xapo.gitrepos.screens.trendingrepos.mvvm.views.TrendingGitRepoListActivity
import com.xapo.gitrepos.utils.UtilFunctions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class TrendingRepoListingViewModel(private val trendingGitRepoListActivity: TrendingGitRepoListActivity) {

  private var apiManager: ApiManager = ApiManager()
  private var iTrendingGitRepoListView: ITrendingGitRepoListView = trendingGitRepoListActivity

  fun fetchTrendingRepos() {

    apiManager.fetchTrendingRepos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<List<RepositoryDetailsModel>>() {
          override fun onNext(response: List<RepositoryDetailsModel>) {
            iTrendingGitRepoListView.showTrendingList(response)
          }

          override fun onError(e: Throwable) {
            UtilFunctions.instance.logger(TrendingRepoListingViewModel::class.java.name, e)
            iTrendingGitRepoListView.hideLoader()
            iTrendingGitRepoListView.showErrorMessage()
          }

          override fun onComplete() {

          }
        })
  }

  fun onItemClick(item: RepositoryDetailsModel) {
    trendingGitRepoListActivity.startActivity(RepoDetailsActivity.makeIntent(trendingGitRepoListActivity, item))
  }
}