package com.grandefirano.gitreposexplorer.view

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.databinding.ActivityDetailsBinding
import com.grandefirano.gitreposexplorer.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(),DetailsContract.DetailsView {

    lateinit var binding:ActivityDetailsBinding

    var repo: Repo?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)


        websiteTextView.setPaintFlags(websiteTextView.getPaintFlags() or Paint.UNDERLINE_TEXT_FLAG)


        //TODO:DOZMIANY FUUUU
        /**
         * TOZMIENIC
         */
        var modelImpl=ExplorerApplication.model
        var detailsViewModel=DetailsViewModel(this,modelImpl)


        binding.viewModel=detailsViewModel

        binding.executePendingBindings()
        detailsViewModel.isServerLimitExceeded.value=false
        detailsViewModel.actualRepo.observe(this, Observer {
            if(it.contributors.isNullOrEmpty())
            detailsViewModel.onInitView(it)

            println("dddd owner detailActiv name "+it.name)
            println("dddd owner detailActiv contri"+it.contributors)

        })

        detailsViewModel.isServerLimitExceeded.observe(this, Observer {

            it?.let {
                if(it) showServerError()
            }

        })

        binding.lifecycleOwner = this

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateDetails() {
        //TODO("Not yet implemented")
    }

    override fun showServerError() {
        Snackbar.make(scrollView,"Server request limit is exceeded",Snackbar.LENGTH_SHORT).show()
    }

    override fun goToMain() {
        finish()
    }

    override fun goToWebsite(website: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
        startActivity(browserIntent)
    }

}
