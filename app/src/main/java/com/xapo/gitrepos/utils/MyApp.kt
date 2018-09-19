package com.xapo.gitrepos.utils

import android.app.Application
import com.xapo.gitrepos.dagger.components.ApiManagerComponent
import com.xapo.gitrepos.dagger.components.DaggerApiManagerComponent
import com.xapo.gitrepos.dagger.components.DaggerMyAppComponent
import com.xapo.gitrepos.dagger.components.MyAppComponent
import com.xapo.gitrepos.dagger.modules.ApiMangerModule
import com.xapo.gitrepos.dagger.modules.MyAppModule

class MyApp : Application() {

  companion object {

    @JvmStatic lateinit var mAppComponent: MyAppComponent
    @JvmStatic lateinit var mApiManagerComponent: ApiManagerComponent
  }

  override fun onCreate() {
    super.onCreate()
    initDagger()
  }

  private fun initDagger() {
    mAppComponent = DaggerMyAppComponent.builder()
        .myAppModule(MyAppModule(this))
        .build()

    mApiManagerComponent = DaggerApiManagerComponent.builder()
        .apiMangerModule(ApiMangerModule())
        .build()
  }

  fun getApplicationComponent(): MyAppComponent {
    return mAppComponent
  }

  fun getApiManagerComponent(): ApiManagerComponent {
    return mApiManagerComponent
  }
}