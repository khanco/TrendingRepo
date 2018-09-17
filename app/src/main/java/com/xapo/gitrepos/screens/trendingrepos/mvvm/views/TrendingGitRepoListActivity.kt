package com.xapo.gitrepos.screens.trendingrepos.mvvm.views

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xapo.gitrepos.R
import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.databinding.ActivityTrendingRepoListBinding
import com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels.TrendingRepoListingViewModel
import com.xapo.gitrepos.utils.UtilFunctions
import kotlinx.android.synthetic.main.activity_trending_repo_list.progressBar
import kotlinx.android.synthetic.main.activity_trending_repo_list.recyclerTrendingRepoList

class TrendingGitRepoListActivity : AppCompatActivity(), ITrendingGitRepoListView, TrendingRepoListAdapter.IRepoSelection {

  private var listRepo: List<RepositoryDetailsModel> = emptyList()
  private lateinit var viewModel: TrendingRepoListingViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initBinding()
    initUI()
    fetchTrendingRepos()
  }

  private fun fetchTrendingRepos() {

    if (!UtilFunctions.instance.isNetworkAvailable(applicationContext)) {
      UtilFunctions.instance.showToast(applicationContext, resources.getString(R.string.no_internet))
      hideLoader()
      return
    }

    viewModel.fetchTrendingRepos()
  }

  private fun initBinding() {
    val activityTrendingRepoListBinding: ActivityTrendingRepoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_trending_repo_list)
    viewModel = TrendingRepoListingViewModel(this)
    activityTrendingRepoListBinding.viewModel = viewModel
  }

  private fun initUI() {
    recyclerTrendingRepoList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
  }

  override fun showTrendingList(response: List<RepositoryDetailsModel>) {
    listRepo = response
    val trendingRepoAdapter = TrendingRepoListAdapter(listRepo)
    trendingRepoAdapter.initInterface(this)
    recyclerTrendingRepoList.adapter = trendingRepoAdapter
    hideLoader()
  }

  override fun showErrorMessage() {
    UtilFunctions.instance.showToast(applicationContext, resources.getString(R.string.generic_error_msg))
  }

  override fun showLoader() {
    progressBar.visibility = View.VISIBLE
  }

  override fun hideLoader() {
    progressBar.visibility = View.GONE
  }

  override fun onItemClick(position: Int) {
    viewModel.onItemClick(listRepo[position])
  }
}

