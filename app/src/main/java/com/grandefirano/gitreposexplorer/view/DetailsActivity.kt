package com.grandefirano.gitreposexplorer.view

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.databinding.ActivityDetailsBinding
import com.grandefirano.gitreposexplorer.viewmodel.DetailsViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity(), DetailsContract.DetailsView {

    private lateinit var binding: ActivityDetailsBinding

    var repo: Repo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)

        /**
         * INIT MVVM & BINDING
         */

        val modelImpl = ExplorerApplication.model
        val detailsViewModel = DetailsViewModel(this, modelImpl)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel


        websiteTextView.paintFlags = (websiteTextView.paintFlags
                or Paint.UNDERLINE_TEXT_FLAG)

        /**
         * CONTRIBUTORS RECYCLERVIEW
         */

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = ContributorsAdapter()
        contributorsRecyclerView.layoutManager = layoutManager
        contributorsRecyclerView.setHasFixedSize(true)
        contributorsRecyclerView.adapter = adapter


        /**
         * OBSERVERS OF LIVE DATA
         */

        detailsViewModel.actualRepo.observe(this, Observer {
            if (it.contributors.isNullOrEmpty())
                detailsViewModel.onInitView(it)
            else
                adapter.submitList(it.contributors)
        })

        detailsViewModel.isServerLimitExceeded.observe(this, Observer {
            it?.let {
                if (it) showServerError()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * SHOW ERROR
     */

    override fun showServerError() {
        Snackbar.make(
            scrollView, "Server request limit is exceeded",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    /**
     * NAVIGATION
     */

    override fun goToMain() {
        finish()
    }

    override fun goToWebsite(website: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(website))
        startActivity(browserIntent)
    }
}
