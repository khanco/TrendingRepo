package com.xapo.gitrepos.dagger.components

import android.app.Application
import com.xapo.gitrepos.dagger.modules.MyAppModule
import dagger.Component

@Component(modules = [MyAppModule::class])
interface MyAppComponent {
  fun app(): Application
}