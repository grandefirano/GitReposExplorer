package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.SerializedName

class RepoSearchResult (

    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val repositories: List<Repo>){

}