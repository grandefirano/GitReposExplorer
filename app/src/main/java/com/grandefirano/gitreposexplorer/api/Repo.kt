package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repo(
    val id: Int,
    val name: String,
    val owner: Owner,
    val language: String,
    val description: String,
    @SerializedName("stargazers_count") val starsCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("html_url") val repositoryUrl: String
) {


    var contributors: List<Owner> = listOf()
    var contributorsCount: Int = 1

}