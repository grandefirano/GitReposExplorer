package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailedRepo(@Expose@SerializedName("id") val id:Int,
                        @Expose@SerializedName("name")val name:String,
                        @Expose@SerializedName("owner")val owner:Owner,
                        @Expose@SerializedName("stargazers_count")val starsCount:Int) {



}
