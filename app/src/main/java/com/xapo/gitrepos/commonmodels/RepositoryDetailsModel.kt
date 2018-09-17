package com.xapo.gitrepos.commonmodels

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import org.parceler.Parcel.Serialization.BEAN
import org.parceler.ParcelConstructor

@Parcel(BEAN)
data class RepositoryDetailsModel @ParcelConstructor constructor(

  @SerializedName("author") var author: String,
  @SerializedName("name") var name: String,
  @SerializedName("url") var url: String,
  @SerializedName("description") var description: String,
  @SerializedName("language") var language: String,
  @SerializedName("stars") var stars: Int,
  @SerializedName("forks") var forks: Int,
  @SerializedName("currentPeriodStars") var currentPeriodStars: Int
)