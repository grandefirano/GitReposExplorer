package com.grandefirano.gitreposexplorer.api

object ApiConstants {

    const val BASE_URL: String = "https://api.github.com"
    const val GET_REPOS: String = "/search/repositories"
    const val GET_DETAILED_REPO:String="/repos/{owner}/{repo}"
    const val GET_REPO_CONTRIBUTORS:String="/repos/{owner}/{repo}/contributors"
}