package com.grandefirano.gitreposexplorer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.contracts.MainContract

class MainActivity : AppCompatActivity(),
    MainContract.MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun updateList() {
        //TODO("Not yet implemented")
    }

    override fun goToDetailsView(id: Int) {
        //TODO("Not yet implemented")
    }
}
