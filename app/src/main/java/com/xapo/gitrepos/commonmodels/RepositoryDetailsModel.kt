package com.xapo.gitrepos.commonmodels

import org.parceler.Parcel
import org.parceler.Parcel.Serialization.BEAN
import org.parceler.ParcelConstructor

@Parcel(BEAN)
data class RepositoryDetailsModel @ParcelConstructor constructor(

  var author: String,
  var name: String,
  var url: String,
  var description: String,
  var language: String,
  var stars: Int,
  var forks: Int,
  var currentPeriodStars: Int
)