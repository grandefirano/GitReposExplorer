package com.grandefirano.gitreposexplorer.api

object ApiConstants {

    const val BASE_URL: String = "https://api.github.com"
    const val GET_REPOS: String = "/search/repositories"
    const val GET_REPO_CONTRIBUTORS:String="/repos/{owner}/{repo}/contributors"

    const val BASE_FILES_URL:String="https://raw.githubusercontent.com"
    const val GET_COLORS_FOR_LANGUAGE:String="/doda/github-language-colors/master/colors"

    const val SIZE_OF_PAGE=9
}