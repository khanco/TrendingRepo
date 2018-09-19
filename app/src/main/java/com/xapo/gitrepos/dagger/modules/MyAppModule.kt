package com.xapo.gitrepos.dagger.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class MyAppModule(private val app: Application) {
  @Provides
  fun provideMyApp() = app
}