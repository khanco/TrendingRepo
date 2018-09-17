package com.xapo.gitrepos.screens.repodetails.mvvm.viewmodels

import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import com.xapo.gitrepos.screens.repodetails.mvvm.views.RepoDetailsActivity
import com.xapo.gitrepos.utils.UtilFunctions

class RepoDetailsViewModel(private val repoDetailsActivity: RepoDetailsActivity) {

  fun setVisibility(data: String): Int {
    return if (TextUtils.isEmpty(data)) GONE else VISIBLE
  }

  fun onMoreDetailsClick(url: String) {
    UtilFunctions.instance.launchWebUrl(repoDetailsActivity, url)
  }
}