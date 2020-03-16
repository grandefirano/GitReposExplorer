package com.grandefirano.gitreposexplorer.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grandefirano.gitreposexplorer.R
import com.grandefirano.gitreposexplorer.api.Owner
import com.grandefirano.gitreposexplorer.databinding.ItemContributorListBinding


class ContributorsAdapter :
    ListAdapter<Owner, ContributorsAdapter.ContributorHolder>(DIFF_CALBACK) {

    companion object {
        val DIFF_CALBACK: DiffUtil.ItemCallback<Owner> = object : DiffUtil.ItemCallback<Owner>() {
            override fun areItemsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Owner, newItem: Owner): Boolean {
                return oldItem.login == newItem.login &&
                        oldItem.avatar == newItem.avatar
            }
        }
    }

    private fun getContributorAt(position: Int): Owner {
        return getItem(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemContributorListBinding>(
                layoutInflater,
                R.layout.item_contributor_list,
                parent,
                false
            )

        return ContributorHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorHolder, position: Int) {

        holder.bind(getContributorAt(position))
    }

    class ContributorHolder(private val binding: ItemContributorListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(owner: Owner) {
            binding.contributor = owner
            binding.executePendingBindings()

        }


    }


}