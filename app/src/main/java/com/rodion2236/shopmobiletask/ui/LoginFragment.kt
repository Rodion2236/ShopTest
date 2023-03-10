package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.FragmentLoginBinding
import com.rodion2236.shopmobiletask.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentLoginBinding = FragmentLoginBinding
            .inflate(inflater, container, false)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLoginBinding.loginButton.setOnClickListener {
            authentication()
            transaction()
        }
    }

    private fun authentication() {
        val firstName = fragmentLoginBinding.loginFirstName.text.toString()
        viewModel.authentication(firstName = firstName).observe(viewLifecycleOwner) { realUser ->
            if (realUser != null && realUser.firstName.isNotEmpty() && realUser.firstName.isNotBlank()) {
                findNavController().navigate(R.id.profile_fragment)
            } else {
                Toast.makeText(requireContext(), "Такого пользователя не существует", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun transaction(): String {
        val name = fragmentLoginBinding.loginFirstName.text.toString()

        if (name.isNotEmpty() && name.isNotBlank()) {
            findNavController().navigate(LoginFragmentDirections.actionLoginToProfile(name))
        }
        return ""
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .remove(this@LoginFragment)
                .commitAllowingStateLoss()
        }
    }
}