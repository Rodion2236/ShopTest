package com.rodion2236.shopmobiletask.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.shopmobiletask.model.latest.InfoLatest
import com.rodion2236.shopmobiletask.model.latest.Latest

class LatestAdapter(private var latest: List<InfoLatest>): RecyclerView.Adapter<LatestAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestAdapter {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return latest.size
    }

    override fun onBindViewHolder(holder: LatestAdapter, position: Int) {
        TODO("Not yet implemented")
    }
}

//займусь ими чуть позже, сначала надо реализовать фрагменты