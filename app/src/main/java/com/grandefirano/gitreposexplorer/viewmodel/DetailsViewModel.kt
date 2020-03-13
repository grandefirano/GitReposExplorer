package com.grandefirano.gitreposexplorer.viewmodel

import androidx.lifecycle.MutableLiveData
import com.grandefirano.gitreposexplorer.api.DetailedRepo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.contracts.MainContract
import com.grandefirano.gitreposexplorer.model.ModelImpl

class DetailsViewModel(val view: DetailsContract.DetailsView,
                       val model: ModelImpl):DetailsContract.DetailsViewModel {

    var actualRepo= model.actualRepo

    override fun onWebsiteClick() {
        TODO("Not yet implemented")
    }

    override fun onInitView() {
        TODO("Not yet implemented")


    }
}