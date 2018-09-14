package com.xapo.gitrepos.screens.trendingrepos.mvp.presenter

import com.xapo.gitrepos.network.ApiManager
import com.xapo.gitrepos.network.RetrofitProvider
import com.xapo.gitrepos.network.Service
import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails
import com.xapo.gitrepos.screens.trendingrepos.mvp.views.ITrendingGitRepoListView
import com.xapo.gitrepos.screens.trendingrepos.mvp.views.TrendingGitRepoListActivity
import com.xapo.gitrepos.utils.UtilFunctions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

class TrendingListPresenter {

  private var apiManager: ApiManager = ApiManager()
  private lateinit var iTrendingGitRepoListView: ITrendingGitRepoListView

  fun initInterface(trendingGitRepoListActivity: TrendingGitRepoListActivity) {
    iTrendingGitRepoListView = trendingGitRepoListActivity
  }

  fun fetchTrendingRepos() {

    apiManager.fetchTrendingRepos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<List<RepositoryDetails>>() {
          override fun onNext(response: List<RepositoryDetails>) {
            iTrendingGitRepoListView.showTrendingList(response)
          }

          override fun onError(e: Throwable) {
            UtilFunctions.instance.logger(TrendingListPresenter::class.java.name, e)
            iTrendingGitRepoListView.hideLoader()
            iTrendingGitRepoListView.showErrorMessage()
          }

          override fun onComplete() {

          }
        })
  }
}