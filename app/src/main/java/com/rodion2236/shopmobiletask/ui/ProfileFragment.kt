package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var fragmentProfileBinding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        fragmentProfileBinding = FragmentProfileBinding
            .inflate(inflater, container, false)
        goShop()
        logOut()
        return fragmentProfileBinding.root
    }

    private fun logOut(){
        fragmentProfileBinding.ivLogOut.setOnClickListener {
            findNavController().navigate(R.id.sign_in_fragment)
        }
        fragmentProfileBinding.tvLogOut.setOnClickListener {
            findNavController().navigate(R.id.sign_in_fragment)
        }
    }

    private fun goShop() {
        fragmentProfileBinding.tvTradeStore.setOnClickListener {
            findNavController().navigate(R.id.page_1_fragment)
        }
        fragmentProfileBinding.ivTradeStore.setOnClickListener {
            findNavController().navigate(R.id.page_1_fragment)
        }
    }
    // может все это в одну кнопку сжать?

    override fun onDestroy() {
        super.onDestroy()

        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .remove(this@ProfileFragment)
                .commitAllowingStateLoss()
        }
    }
}