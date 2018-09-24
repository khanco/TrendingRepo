package com.xapo.gitrepos.screens.trendingrepos.mvvm.views

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.xapo.gitrepos.R
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.databinding.ActivityTrendingRepoListBinding
import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import com.xapo.gitrepos.screens.trendingrepos.dagger.DaggerRepoListComponent
import com.xapo.gitrepos.screens.trendingrepos.dagger.RepoListModule
import com.xapo.gitrepos.screens.trendingrepos.mvvm.viewmodels.TrendingRepoListingViewModel
import com.xapo.gitrepos.utils.MyApp
import com.xapo.gitrepos.utils.UtilFunctions
import kotlinx.android.synthetic.main.activity_trending_repo_list.recyclerTrendingRepoList
import javax.inject.Inject

class TrendingGitRepoListActivity : AppCompatActivity(), TrendingRepoListAdapter.IRepoSelection {

  @Inject
  lateinit var viewModel: TrendingRepoListingViewModel

  private lateinit var repoList: List<RepositoryDetailsModel>

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
    viewModel.getData().observe(this, Observer { showTrendingList(it!!) })
  }

  private fun fetchTrendingRepos() {

    if (!UtilFunctions.instance.isNetworkAvailable(applicationContext)) {
      UtilFunctions.instance.showToast(applicationContext, resources.getString(R.string.no_internet))
      return
    }

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

  private fun showTrendingList(repoList: List<RepositoryDetailsModel>) {
    this.repoList = repoList
    val trendingRepoAdapter = TrendingRepoListAdapter(repoList)
    trendingRepoAdapter.initInterface(this)
    recyclerTrendingRepoList.adapter = trendingRepoAdapter
  }

  override fun onItemClick(position: Int) {
    startActivity(RepoDetailsActivity.makeIntent(applicationContext, repoList[position]))
  }
}

