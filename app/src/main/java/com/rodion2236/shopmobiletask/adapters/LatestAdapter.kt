package com.rodion2236.shopmobiletask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodion2236.shopmobiletask.databinding.ListLatestBinding
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

    private var listLatest = mutableListOf<InfoLatest>()

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
}
//займусь ими чуть позже, сначала надо реализовать фрагменты