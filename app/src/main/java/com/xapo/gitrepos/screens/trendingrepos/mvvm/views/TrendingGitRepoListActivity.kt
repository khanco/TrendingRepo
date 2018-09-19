package com.xapo.gitrepos.screens.trendingrepos.mvvm.views

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xapo.gitrepos.R
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.databinding.ActivityTrendingRepoListBinding
import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import com.xapo.gitrepos.screens.trendingrepos.dagger.DaggerRepoListComponent
import com.xapo.gitrepos.screens.trendingrepos.dagger.RepoListModule
import com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels.TrendingRepoListingViewModel
import com.xapo.gitrepos.utils.GenericStatus
import com.xapo.gitrepos.utils.GenericStatus.ERROR
import com.xapo.gitrepos.utils.GenericStatus.NO_NETWORK
import com.xapo.gitrepos.utils.GenericStatus.SUCCESS
import com.xapo.gitrepos.utils.MyApp
import com.xapo.gitrepos.utils.UtilFunctions
import kotlinx.android.synthetic.main.activity_trending_repo_list.progressBar
import kotlinx.android.synthetic.main.activity_trending_repo_list.recyclerTrendingRepoList
import javax.inject.Inject

class TrendingGitRepoListActivity : AppCompatActivity(), TrendingRepoListAdapter.IRepoSelection {

  @Inject
  lateinit var viewModel: TrendingRepoListingViewModel

  private var listRepo: List<RepositoryDetailsModel> = emptyList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initDagger()
    initBinding()
    initUI()
    initObservation()
    fetchTrendingRepos()
  }

  private fun initDagger() {
    DaggerRepoListComponent.builder()
        .apiManagerComponent((application as MyApp).getApiManagerComponent())
        .repoListModule(RepoListModule())
        .build()
        .inject(this)
  }

  private fun initObservation() {
    viewModel.getStatus().observe(this, Observer { handleStatus(it) })
    viewModel.getData().observe(this, Observer { showTrendingList(it!!) })
  }

  private fun handleStatus(status: GenericStatus?) {
    var msg = ""
    when (status) {
      NO_NETWORK -> msg = resources.getString(R.string.no_internet)
      SUCCESS -> msg = resources.getString(R.string.success)
      ERROR -> msg = resources.getString(R.string.generic_error_msg)
    }

    hideLoader()
    UtilFunctions.instance.showToast(applicationContext, msg)
  }

  private fun fetchTrendingRepos() {

    if (!UtilFunctions.instance.isNetworkAvailable(applicationContext)) {
      UtilFunctions.instance.showToast(
          applicationContext, resources.getString(R.string.no_internet)
      )
      hideLoader()
      return
    }

    showLoader()
    viewModel.fetchTrendingRepos()
  }

  private fun initBinding() {
    val activityTrendingRepoListBinding: ActivityTrendingRepoListBinding = DataBindingUtil.setContentView(
        this, R.layout.activity_trending_repo_list
    )
    activityTrendingRepoListBinding.viewModel = viewModel
  }

  private fun initUI() {
    recyclerTrendingRepoList.layoutManager = LinearLayoutManager(
        applicationContext, LinearLayoutManager.VERTICAL, false
    )
  }

  private fun showTrendingList(response: List<RepositoryDetailsModel>) {
    listRepo = response
    val trendingRepoAdapter = TrendingRepoListAdapter(listRepo)
    trendingRepoAdapter.initInterface(this)
    recyclerTrendingRepoList.adapter = trendingRepoAdapter
    hideLoader()
  }

  private fun showLoader() {
    progressBar.visibility = View.VISIBLE
  }

  private fun hideLoader() {
    progressBar.visibility = View.GONE
  }

  override fun onItemClick(position: Int) {
    startActivity(RepoDetailsActivity.makeIntent(applicationContext, listRepo[position]))
  }
}

