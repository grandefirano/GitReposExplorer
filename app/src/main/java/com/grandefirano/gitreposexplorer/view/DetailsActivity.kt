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
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.grandefirano.gitreposexplorer.ExplorerApplication
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.contracts.DetailsContract
import com.grandefirano.gitreposexplorer.contracts.Model
import com.grandefirano.gitreposexplorer.databinding.ActivityDetailsBinding
import com.grandefirano.gitreposexplorer.model.ModelImpl
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
         * INIT MVVM & ADAPTER
         */

        val modelImpl = ModelImpl
        val detailsViewModel = DetailsViewModel(this, modelImpl)

        websiteTextView.paintFlags = (websiteTextView.paintFlags
                or Paint.UNDERLINE_TEXT_FLAG)


        val adapter=ContributorsAdapter()

        /**
         * INIT FUNCTIONS
         */
        initBinding(detailsViewModel)
        initContributorsRecyclerView(ContributorsAdapter())
        initObserversOfLiveData(detailsViewModel,adapter)

    }
    /**
     * INIT OBSERVERS OF LIVE DATA
     */

    private fun initObserversOfLiveData(detailsViewModel: DetailsViewModel,adapter: ContributorsAdapter) {
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

    /**
     * INIT CONTRIBUTORS RECYCLERVIEW
     */
    private fun initContributorsRecyclerView(adapter:ContributorsAdapter) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        contributorsRecyclerView.layoutManager = layoutManager
        contributorsRecyclerView.setHasFixedSize(true)
        contributorsRecyclerView.adapter = adapter
    }

    /**
     * INIT BINDING
     */
    private fun initBinding(detailsViewModel: DetailsViewModel) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        binding.lifecycleOwner = this
        binding.viewModel = detailsViewModel

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
