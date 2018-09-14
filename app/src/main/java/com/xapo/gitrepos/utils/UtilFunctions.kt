package com.xapo.gitrepos.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.util.Log
import android.widget.Toast

class UtilFunctions private constructor() {

  init { println("This ($this) is a singleton") }

  private object Holder { val INSTANCE = UtilFunctions() }

  companion object {
    val instance: UtilFunctions by lazy { Holder.INSTANCE }
  }

  fun showToast(context: Context, message: String) {
      Toast.makeText(context, message, Toast.LENGTH_LONG).show()
  }

  fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
  }

  fun logger(className: String, e: Throwable) {
    Log.e(className, e.message)
    e.printStackTrace()
  }

  fun launchWebUrl(applicationContext: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)
    applicationContext.startActivity(intent)
  }
}