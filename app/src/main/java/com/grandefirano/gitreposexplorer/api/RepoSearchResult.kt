package com.grandefirano.gitreposexplorer.api

import com.google.gson.annotations.SerializedName

class RepoSearchResult (
    @SerializedName("items") val repositories: List<Repo>)