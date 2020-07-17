package com.grandefirano.gitreposexplorer.features.searchRepo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Repo
import com.grandefirano.gitreposexplorer.databinding.ItemMainListBinding

class MainRecyclerViewAdapter(private val viewModel: MainViewModel) :
    ListAdapter<Repo, MainRecyclerViewAdapter.RepoHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Repo> = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.name == newItem.name &&
                        oldItem.repositoryUrl == oldItem.repositoryUrl
                        && oldItem.owner.login == oldItem.owner.login
                        && oldItem.updatedAt == newItem.updatedAt
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).starsCount>500){
            R.layout.item_main_list
        }else{
            R.layout.item_main_list
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMainListBinding>(
                layoutInflater,
                viewType,
                parent,
                false
            )


        return RepoHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {

        holder.bind(viewModel, getItem(position))
    }

    class RepoHolder(private val binding: ItemMainListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(viewModel: MainViewModel, repo: Repo) {

            binding.viewModel = viewModel
            binding.repo = repo
            binding.executePendingBindings()
        }
    }
}