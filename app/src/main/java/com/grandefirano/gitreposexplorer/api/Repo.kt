package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Repo (@SerializedName("id") val id:Int,
                 @SerializedName("name")val name:String,
                 @SerializedName("owner")val owner:Owner,
                 @SerializedName("stargazers_count")val starsCount:Int) {


    lateinit var contributors:List<Owner>

}