package com.xapo.gitrepos.screens.repodetails.mvvm.views

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xapo.gitrepos.R
import com.xapo.gitrepos.commonmodels.RepositoryDetailsModel
import com.xapo.gitrepos.databinding.ActivityRepoDetailsBinding
import com.xapo.gitrepos.screens.repodetails.mvvm.viewmodels.RepoDetailsViewModel
import com.xapo.gitrepos.utils.Constants
import com.xapo.gitrepos.utils.UtilFunctions
import org.parceler.Parcels

class RepoDetailsActivity : AppCompatActivity(), IRepoDetailsCallbacks {

  private lateinit var repo: RepositoryDetailsModel

  companion object {

    fun makeIntent(context: Context, repo: RepositoryDetailsModel): Intent {
      val intent = Intent(context, RepoDetailsActivity::class.java)
      intent.putExtra(Constants.REPO_DATA, Parcels.wrap(RepositoryDetailsModel::class.java, repo))
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initIntentData()
    initBinding()
  }

  private fun initIntentData() {
    repo = Parcels.unwrap(intent.extras.getParcelable(Constants.REPO_DATA))
  }

  private fun initBinding() {
    val activityRepoDetailsBinding: ActivityRepoDetailsBinding = DataBindingUtil.setContentView(this,  R.layout.activity_repo_details)
    activityRepoDetailsBinding.callback = this
    activityRepoDetailsBinding.model = repo
    activityRepoDetailsBinding.viewModel = RepoDetailsViewModel()
  }

  override fun onMoreDetailsClick(url: String) {
    UtilFunctions.instance.launchWebUrl(this, url)
  }
}