package com.grandefirano.gitreposexplorer.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.model.ModelImpl
import com.grandefirano.gitreposexplorer.viewmodel.DetailsViewModel

class DetailsActivity : AppCompatActivity(),DetailsContract.DetailsView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)

        val owner=intent.getStringExtra(ViewConstants.INTENT_OWNER)
        val repoName=intent.getStringExtra(ViewConstants.INTENT_REPOSITORY_NAME)

        //TODO:DOZMIANY FUUUU
        /**
         * TOZMIENIC
         */
        var modelImpl=ModelImpl()
        var detailsViewModel=DetailsViewModel(this,modelImpl)

        detailsViewModel.actualRepo.observe(this, Observer {
//            adapter.repos=it
//            adapter.notifyDataSetChanged()
            updateDetails()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateDetails() {
        TODO("Not yet implemented")
    }

    override fun goToMain() {
        finish()
    }

}
