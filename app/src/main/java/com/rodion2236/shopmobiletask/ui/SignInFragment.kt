package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rodion2236.shopmobiletask.R
import com.rodion2236.shopmobiletask.databinding.FragmentSignInBinding
import com.rodion2236.shopmobiletask.model.User
import com.rodion2236.shopmobiletask.viewmodel.SignViewModel

class SignInFragment : Fragment() {

    private lateinit var fragmentSignInBinding: FragmentSignInBinding
    private lateinit var viewModel: SignViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentSignInBinding = FragmentSignInBinding
            .inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[SignViewModel::class.java]

        fragmentSignInBinding.signInLogin.setOnClickListener{
            findNavController().navigate(R.id.login_fragment)
        }
        return fragmentSignInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSignInBinding.button.setOnClickListener {
            addData()
            cleanFields()
        }
    }

    //проверить валидность емайл, чистка после добавки, добавление в базу, проверка всех полей на пустоту

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun cleanFields(){
        fragmentSignInBinding.signInFirstName.text?.clear()
        fragmentSignInBinding.signInLastName.text?.clear()
        fragmentSignInBinding.signInEmail.text?.clear()
    }

    private fun checkUser(){
        val email = fragmentSignInBinding.signInEmail.text.toString()
        val firstName = fragmentSignInBinding.signInFirstName.text.toString()
        val userLiveData = viewModel.getEmailAndFirstName(email, firstName)
        userLiveData.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                return@Observer
            }
        })
    }

    private fun checkingFields(firstName: String, lastName: String, email: String): Boolean {
        return !(firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || !isValidEmail(email))
    }

    private fun addData() {
        val firstName = fragmentSignInBinding.signInFirstName.text.toString()
        val lastName = fragmentSignInBinding.signInLastName.text.toString()
        val email = fragmentSignInBinding.signInEmail.text.toString()

        if (checkingFields(firstName, lastName, email)) {

            val user = User(0, firstName, lastName, email)

            viewModel.getEmailAndFirstName(email,firstName).observe(viewLifecycleOwner) { newUser ->
                if (newUser == null) {
                    viewModel.addUserToDatabase(user)
                    findNavController().navigate(R.id.profile_fragment)
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Пользователь с таким именем уже существует",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(requireContext(), "Поля заполнены неправильно", Toast.LENGTH_LONG).show()
        }
        checkUser()
    }

    override fun onDestroy() {
        super.onDestroy()

        activity?.run {
            supportFragmentManager
                .beginTransaction()
                .remove(this@SignInFragment)
                .commitAllowingStateLoss()
        }
    }
}
