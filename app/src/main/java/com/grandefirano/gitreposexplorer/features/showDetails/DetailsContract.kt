package com.grandefirano.gitreposexplorer.features.showDetails

import com.grandefirano.gitreposexplorer.api.Repo

interface DetailsContract {
    interface DetailsView {

        fun showServerError()
        fun goToMain()
        fun goToWebsite(website: String)
    }

    interface DetailsViewModel {
        fun onWebsiteClick(website: String)
        fun onInitView(repo: Repo)
    }
}