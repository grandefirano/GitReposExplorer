package com.grandefirano.gitreposexplorer.viewmodel

import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.model.ModelImpl


class DetailsViewModel(val view: DetailsContract.DetailsView,
                       val model: ModelImpl):DetailsContract.DetailsViewModel {

    var actualRepo=model.actualRepo
    var isServerLimitExceeded=model.isServerLimitExceeded

    override fun onWebsiteClick(website:String) {
        view.goToWebsite(website)
    }

    override fun onInitView(repo:Repo) {
        model.getContributors(repo)
    }
}