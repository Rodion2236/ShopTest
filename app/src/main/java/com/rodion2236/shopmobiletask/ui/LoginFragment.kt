package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.FragmentLoginBinding
import com.rodion2236.shopmobiletask.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var fragmentLoginBinding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return fragmentLoginBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentLoginBinding.loginButton.setOnClickListener {
            authentication()
        }
    }

    override fun onDestroy() {
        Log.d("login_fragment", "onDestroy")
        super.onDestroy()
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
}