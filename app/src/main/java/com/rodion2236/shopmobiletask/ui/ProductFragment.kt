package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodion2236.shopmobiletask.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private lateinit var productBinding: FragmentProductBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        productBinding = FragmentProductBinding
            .inflate(inflater, container, false)
        return productBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .remove(this@ProductFragment)
                .commitAllowingStateLoss()
        }
    }
}