package com.xapo.gitrepos.dagger.components

import com.xapo.gitrepos.dagger.modules.ApiMangerModule
import com.xapo.gitrepos.network.ApiManager
import dagger.Component

@Component (modules = [ApiMangerModule::class])
interface ApiManagerComponent {
  fun apiManager(): ApiManager
}