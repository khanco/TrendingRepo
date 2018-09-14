package com.xapo.gitrepos.screens.trendingrepos.mvp.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.xapo.gitrepos.R
import com.xapo.gitrepos.R.layout
import com.xapo.gitrepos.screens.repodetails.RepoDetailsActivity
import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails
import com.xapo.gitrepos.screens.trendingrepos.mvp.presenter.TrendingListPresenter
import com.xapo.gitrepos.utils.UtilFunctions
import kotlinx.android.synthetic.main.activity_trending_repo_list.progressBar
import kotlinx.android.synthetic.main.activity_trending_repo_list.recyclerTrendingRepoList

class TrendingGitRepoListActivity : AppCompatActivity(), ITrendingGitRepoListView, TrendingRepoListAdapter.IRepoSelection {

  private lateinit var trendingListPresenter: TrendingListPresenter
  private var listRepo: List<RepositoryDetails> = emptyList()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_trending_repo_list)
    initUI()
    initPresenter()
  }

  private fun initUI() {
    recyclerTrendingRepoList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
  }

  private fun initPresenter() {

    if (!UtilFunctions.instance.isNetworkAvailable(applicationContext)) {
      UtilFunctions.instance.showToast(applicationContext, resources.getString(R.string.no_internet))
      hideLoader()
      return
    }

    trendingListPresenter = TrendingListPresenter()
    trendingListPresenter.initInterface(this)
    trendingListPresenter.fetchTrendingRepos()
  }

  override fun showTrendingList(response: List<RepositoryDetails>) {
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

  override fun onRepoSelected(position: Int) {
    startActivity(RepoDetailsActivity.makeIntent(applicationContext, listRepo[position]))
  }
}

