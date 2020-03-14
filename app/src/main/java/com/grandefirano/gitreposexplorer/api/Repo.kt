package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repo (@SerializedName("id") val id:Int,
                 @SerializedName("name")val name:String,
                 @SerializedName("owner")val owner:Owner,
                 @SerializedName("stargazers_count")val starsCount:Int,
                 @SerializedName("language")val language:String,
                 @SerializedName("watchers_count")val watchersCount:Int,
                 @SerializedName("forks_count")val forksCount:Int,
                 @SerializedName("updated_at")val updatedAt:String,
                 @SerializedName("html_url")val repositoryUrl:String,
                 @SerializedName("description")val description:String) {


    lateinit var contributors:List<Owner>

}