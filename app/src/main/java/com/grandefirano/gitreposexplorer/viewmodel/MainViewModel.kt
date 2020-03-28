package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.grandefirano.gitreposexplorer.api.ApiConstants
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.contracts.Model


class MainViewModel(val view: MainContract.MainView, val model: Model) :
    ViewModel(), MainContract.MainViewModel {

    val filteredRepositories: LiveData<List<Repo>> = model.repos
    override var actualSearchText: String = ""
    var actualPage: Int = 1
    override val isServerLimitExceeded = model.isServerLimitExceeded
    override var sortListBy: String = ""


    override fun onQueryChange() {
        actualPage = 1

        if (actualSearchText != "") {

            model.getRepositories(actualSearchText, sortListBy, 1)
            view.showWelcomeScreen(false)
        } else onViewInit()
    }

    override fun onViewInit() {
        view.showWelcomeScreen(true)
        view.showList(false)
        view.showNoResults(false)
        view.showServerError(false)
    }

    override fun onSortByStateChanged(sort: String) {
        sortListBy = sort
        onQueryChange()
    }

    override fun onQueryTextChanged(searchText: String) {
        actualSearchText = searchText
        onQueryChange()
    }

    override fun onRepoClicked(repo: Repo) {
        model.setActualRepository(repo)
        view.goToDetailsView()
    }

    override fun loadMoreItems(totalItemCount: Int, lastVisiblePos: Int) {
        if (
            lastVisiblePos
            == totalItemCount - 1
            && totalItemCount >= ApiConstants.SIZE_OF_PAGE * actualPage
        ) {
            actualPage++
            model.getRepositories(actualSearchText, sortListBy, actualPage)
        }
    }

}