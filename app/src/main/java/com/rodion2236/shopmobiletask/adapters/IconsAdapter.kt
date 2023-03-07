package com.rodion2236.shopmobiletask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodion2236.shopmobiletask.databinding.ListIconBinding
import com.rodion2236.shopmobiletask.model.Icons

class IconsAdapter(
    private var icons: List<Icons>
    ): RecyclerView.Adapter<IconsAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val iconsBinding: ListIconBinding
    ):RecyclerView.ViewHolder(iconsBinding.root) {
        fun bind(icons: Icons) {
            iconsBinding.ivIcon.setImageResource(icons.iconsId)
            iconsBinding.tvIcon.text = icons.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconsAdapter.ViewHolder {
        val iconBinding = ListIconBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return ViewHolder(iconBinding)
    }

    override fun onBindViewHolder(holder: IconsAdapter.ViewHolder, position: Int) {
        holder.bind(icons[position])
    }

    override fun getItemCount(): Int {
        return icons.size
    }
}