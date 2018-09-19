package com.xapo.gitrepos.screens.repodetails.mvvm.viewmodels

import android.text.TextUtils
import android.view.View.GONE
import android.view.View.VISIBLE

class RepoDetailsViewModel {

  fun setVisibility(data: String): Int {
    return if (TextUtils.isEmpty(data)) GONE else VISIBLE
  }

  fun onMoreDetailsClick(url: String) {
    
  }
}