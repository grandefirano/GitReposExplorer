package com.grandefirano.gitreposexplorer.view

import android.graphics.Paint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        println("owner detailActiv "+modelImpl.toString())

        binding.viewModel=detailsViewModel
        binding.executePendingBindings()
        //val position:Int=intent.getIntExtra("id",0)

        detailsViewModel.onInitView()
        println("owner detailActiv "+detailsViewModel.actualRepo.value.toString())
        detailsViewModel.actualRepo.observe(this, Observer {
            println("owner detailActiv "+it.name)
           // println("owner detailActiv "+it.contributors[0]?.login)
        })

        binding.lifecycleOwner = this
        ///



    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.home->onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateDetails() {
        //TODO("Not yet implemented")
    }

    override fun goToMain() {
        finish()
    }

}
