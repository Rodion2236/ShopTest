package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rodion2236.shopmobiletask.adapters.FlashAdapter
import com.rodion2236.shopmobiletask.adapters.IconsAdapter
import com.rodion2236.shopmobiletask.adapters.LatestAdapter
import com.rodion2236.shopmobiletask.databinding.FragmentShopBinding
import com.rodion2236.shopmobiletask.model.Icons
import com.rodion2236.shopmobiletask.viewmodel.ShopViewModel


class ShopFragment : Fragment() {

    private lateinit var shopBinding: FragmentShopBinding

    private val shopViewModel: ShopViewModel by viewModels()

    private lateinit var iconsAdapter: IconsAdapter
    private lateinit var flashAdapter: FlashAdapter
    private lateinit var latestAdapter: LatestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iconsAdapter = IconsAdapter(Icons.values().toList())
        flashAdapter = FlashAdapter()
        latestAdapter = LatestAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        shopBinding = FragmentShopBinding
            .inflate(inflater, container, false)

        onAttachRecyclerViewIcon()
        onAttachRecyclerViewLatest()
        onAttachRecyclerViewFlash()

        return shopBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shopViewModel.observeLatest().observe(viewLifecycleOwner) { latest ->
            latest?.let {
                latestAdapter.updateLatest(it)
            }
        }

        shopViewModel.observeFlash().observe(viewLifecycleOwner) { flash ->
            flash?.let {
                flashAdapter.submitList(it)
            }
        }
    }

    private fun onAttachRecyclerViewIcon() = shopBinding.recyclerIcons.apply {
        iconsAdapter = IconsAdapter(Icons.values().toList())
        shopBinding.recyclerIcons.adapter = iconsAdapter
        shopBinding.recyclerIcons.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun onAttachRecyclerViewLatest() = shopBinding.recyclerLatest.apply {
        latestAdapter = LatestAdapter()
        adapter = latestAdapter
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    private fun onAttachRecyclerViewFlash() = shopBinding.recyclerFlash.apply {
        flashAdapter = FlashAdapter()
        adapter = flashAdapter
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL, false
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .remove(this@ShopFragment)
                .commitAllowingStateLoss()
        }
    }
}