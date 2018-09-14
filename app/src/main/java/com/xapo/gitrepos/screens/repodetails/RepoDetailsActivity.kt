package com.xapo.gitrepos.screens.repodetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xapo.gitrepos.R
import com.xapo.gitrepos.screens.trendingrepos.mvp.model.RepositoryDetails
import com.xapo.gitrepos.utils.Constants
import com.xapo.gitrepos.utils.UtilFunctions
import kotlinx.android.synthetic.main.activity_repo_details.*
import org.parceler.Parcels

class RepoDetailsActivity : AppCompatActivity() {

  private lateinit var repo: RepositoryDetails

  companion object {

    fun makeIntent(context: Context, repo: RepositoryDetails): Intent {
      val intent = Intent(context, RepoDetailsActivity::class.java)
      intent.putExtra(Constants.REPO_DATA, Parcels.wrap(RepositoryDetails::class.java, repo))
      return intent
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_repo_details)
    initListeners()
    initIntentData()
    showDetails()
  }

  private fun showDetails() {
    tvTitle.text = repo.name
    tvAuthor.text = String.format(resources.getString(R.string.author_name), repo.author)
    tvLanguage.text = String.format(resources.getString(R.string.language), repo.language)
    tvStars.text = String.format(resources.getString(R.string.stars), repo.stars)
    tvForks.text = String.format(resources.getString(R.string.forks), repo.forks)
    tvDesc.text = String.format(resources.getString(R.string.desc), repo.description)
  }

  private fun initListeners() {
    tvMoreDetails.setOnClickListener {
      UtilFunctions.instance.launchWebUrl(applicationContext, repo.url)
    }
  }

  private fun initIntentData() {
    repo = Parcels.unwrap(intent.extras.getParcelable(Constants.REPO_DATA))
  }
}