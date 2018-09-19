package com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.network.ApiManager
import com.xapo.gitrepos.utils.GenericStatus
import com.xapo.gitrepos.utils.GenericStatus.ERROR
import com.xapo.gitrepos.utils.GenericStatus.SUCCESS
import com.xapo.gitrepos.utils.UtilFunctions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TrendingRepoListingViewModel @Inject internal constructor(private val apiManager: ApiManager) {

  private val status = MutableLiveData<GenericStatus>()
  private val data = MutableLiveData<List<RepositoryDetailsModel>>()

  fun getStatus(): LiveData<GenericStatus> = status
  fun getData(): LiveData<List<RepositoryDetailsModel>> = data

  fun fetchTrendingRepos() {

    apiManager.fetchTrendingRepos()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object : DisposableObserver<List<RepositoryDetailsModel>>() {
          override fun onNext(response: List<RepositoryDetailsModel>) {
            status.value = SUCCESS
            data.value = response
          }

          override fun onError(e: Throwable) {
            UtilFunctions.instance.logger(TrendingRepoListingViewModel::class.java.name, e)
            status.value = ERROR
          }

          override fun onComplete() {

          }
        })
  }
}