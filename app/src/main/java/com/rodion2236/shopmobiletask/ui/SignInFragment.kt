package com.rodion2236.shopmobiletask.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodion2236.shopmobiletask.databinding.FragmentSignInBinding

class SignInFragment : Fragment() {

    private var _fragmentSignInBinding: FragmentSignInBinding? = null
    private val fragmentSignInBinding = checkNotNull(_fragmentSignInBinding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentSignInBinding = FragmentSignInBinding.inflate(inflater, container, false)
        return fragmentSignInBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSignInBinding.button.setOnClickListener {

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _fragmentSignInBinding = null
    }
}
//проверить валидность емайл, чистка после добавки, добавление в базу, проверка всех полей на пустоту