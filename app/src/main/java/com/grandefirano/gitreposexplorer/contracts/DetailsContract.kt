package com.grandefirano.gitreposexplorer.contracts

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