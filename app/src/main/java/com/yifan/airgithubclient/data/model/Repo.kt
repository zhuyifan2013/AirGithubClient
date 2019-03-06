package com.yifan.airgithubclient.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Repo(
        @Json(name = "name") val repoName: String,
        @Json(name = "description") val repoDescription: String?
) : Parcelable