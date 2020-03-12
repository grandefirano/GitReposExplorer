package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.SerializedName

data class Repo (@SerializedName("id") val id:Int,
                 @SerializedName("name")val name:String,
                 @SerializedName("owner")val owner:Owner) {
}