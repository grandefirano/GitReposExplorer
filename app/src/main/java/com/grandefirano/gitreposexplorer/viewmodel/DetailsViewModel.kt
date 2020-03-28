package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.ViewModel
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.model.ModelImpl


class DetailsViewModel(
    val view: DetailsContract.DetailsView,
    private val model: ModelImpl
) : ViewModel(), DetailsContract.DetailsViewModel {

    val actualRepo = model.actualRepo
    val isServerLimitExceeded = model.isServerLimitExceeded

    override fun onWebsiteClick(website: String) {
        view.goToWebsite(website)
    }

    override fun onInitView(repo: Repo) {
        model.getContributors(repo)
    }
}