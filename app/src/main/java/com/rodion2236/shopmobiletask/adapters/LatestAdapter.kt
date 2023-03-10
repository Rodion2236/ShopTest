package com.rodion2236.shopmobiletask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodion2236.shopmobiletask.databinding.ListLatestBinding
import com.rodion2236.shopmobiletask.model.flash.InfoFlash
import com.rodion2236.shopmobiletask.model.latest.InfoLatest
import com.rodion2236.shopmobiletask.model.latest.Latest

class LatestAdapter(): RecyclerView.Adapter<LatestAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val latestBinding: ListLatestBinding
    ): RecyclerView.ViewHolder(latestBinding.root) {

        fun bind(infoLatest: InfoLatest) {
            Glide.with(latestBinding.root)
                .load(infoLatest.image_url)
                .centerCrop()
                .into(latestBinding.ivConsole)

            latestBinding.tvConsole.text = infoLatest.category
            latestBinding.tvNameConsole.text = infoLatest.name
            latestBinding.tvCost.text = infoLatest.price.toString()
        }
    }

    private var listLatest = listOf<InfoLatest>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestAdapter.ViewHolder {
        val binding = ListLatestBinding
            .inflate(LayoutInflater
            .from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listLatest.size
    }

    override fun onBindViewHolder(holder: LatestAdapter.ViewHolder, position: Int) {
        holder.bind(listLatest[position])
    }

    private class Comparator(
        private val oldList: List<InfoLatest>,
        private val newList: List<InfoLatest>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    fun updateLatest(newList: List<InfoLatest>) {
        val diffResult = DiffUtil.calculateDiff(
            Comparator(listLatest, newList)
        )
        listLatest = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
//займусь ими чуть позже, сначала надо реализовать фрагменты