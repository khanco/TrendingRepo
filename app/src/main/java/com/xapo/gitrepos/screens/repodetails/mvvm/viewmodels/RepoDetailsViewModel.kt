package com.xapo.gitrepos.screens.repodetails.mvvm.viewmodels

import android.content.Context
import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE
import com.xapo.gitrepos.utils.UtilFunctions

class RepoDetailsViewModel {

  fun setVisibility(data: String): Int {
    return if (TextUtils.isEmpty(data)) GONE else VISIBLE
  }

  fun onMoreDetailsClick(url: String) {

  }
}