package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.SerializedName

data class Owner(@SerializedName("login")val login:String,
                 @SerializedName("avatar_url") val avatar:String) {
}