package com.grandefirano.gitreposexplorer.features.showDetails

import androidx.lifecycle.ViewModel
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.shared.model.ModelImpl


class DetailsViewModel(
    private val view: DetailsContract.DetailsView,
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