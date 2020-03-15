package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.LiveData
import com.grandefirano.gitreposexplorer.api.ApiConstants
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class MainViewModel(val view:MainContract.MainView,val model: ModelImpl):MainContract.MainViewModel {

    var filteredRepositories:LiveData<List<Repo>> = model.repos
    var actualSearchText:String=""
    var actualPage:Int=1

    override fun onQueryChange(searchText: String) {
        actualPage=1
        actualSearchText=searchText
        if(searchText!=null&& searchText!="") {
            //TODO ZMIENIC
            model.getRepositories(searchText,"s","s",1)
            view.showList()
        }
        else onViewInit()
    }

    override fun onViewInit() {
        view.showWelcomeScreen()
    }

    override fun onSortByClicked() {
        TODO("Not yet implemented")
    }

    override fun onRepoClicked(repo:Repo) {

        model.setActualRepository(repo)
        view.goToDetailsView()
    }

    override fun loadMoreItems(totalItemCount: Int, lastVisiblePos: Int) {

        println("ddd mnoze ${ApiConstants.SIZE_OF_PAGE*actualPage}")
        println("ddd ${totalItemCount}")
        if(
            lastVisiblePos
            ==totalItemCount-1
            &&totalItemCount>= ApiConstants.SIZE_OF_PAGE*actualPage) {
            actualPage++
            model.getRepositories(actualSearchText, "s", "s", actualPage)
        }
        println("dddd $actualPage")

    }

    override fun onNoItemInList(b: Boolean) {
        if(b&& actualSearchText.isNotEmpty()){
            println("ddd show $actualSearchText")
            view.showNoResults(actualSearchText)
        }else{
            view.hideNoResult()
        }
    }


}