package com.grandefirano.gitreposexplorer.contracts

import com.grandefirano.gitreposexplorer.api.Repo

interface DetailsContract {
    interface DetailsView{

        fun updateDetails()

        fun goToMain()
    }
    interface DetailsViewModel{
        fun onWebsiteClick()
        fun onInitView()
    }
}