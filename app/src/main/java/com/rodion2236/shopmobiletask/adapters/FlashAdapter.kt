package com.rodion2236.shopmobiletask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rodion2236.shopmobiletask.databinding.ListFlashBinding
import com.rodion2236.shopmobiletask.model.flash.InfoFlash

class FlashAdapter(): RecyclerView.Adapter<FlashAdapter.ViewHolder>() {

    inner class ViewHolder(
        private val flashBinding: ListFlashBinding
        ): RecyclerView.ViewHolder(flashBinding.root) {
            fun bind(infoFlash: InfoFlash) {

                Glide.with(flashBinding.root)
                    .load(infoFlash.image_url)
                    .centerCrop()
                    .into(flashBinding.ivSneakers)

                flashBinding.tvDiscount.text = "${infoFlash.discount}% off"
                flashBinding.tvCategory.text = infoFlash.category
                flashBinding.tvSneakers.text = infoFlash.name
                flashBinding.tvCost.text = infoFlash.price.toString()
            }
    }

    private var listFlash = listOf<InfoFlash>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlashAdapter.ViewHolder {
        val binding = ListFlashBinding
            .inflate(LayoutInflater
                .from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlashAdapter.ViewHolder, position: Int) {
        holder.bind(listFlash[position])
    }

    override fun getItemCount(): Int {
        return listFlash.size
    }

    private class Comparator(
        private val oldList: List<InfoFlash>,
        private val newList: List<InfoFlash>
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

    fun submitList(newList: List<InfoFlash>) {
        val diffResult = DiffUtil.calculateDiff(
            Comparator(listFlash, newList)
        )
        listFlash = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
//займусь ими чуть позже, сначала надо реализовать фрагменты