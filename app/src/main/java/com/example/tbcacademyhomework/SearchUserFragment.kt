package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.tbcacademyhomework.databinding.FragmentSearchUserBinding


class SearchUserFragment : Fragment() {

    private var _binding: FragmentSearchUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchUserBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setup()


    }

    private fun setup() {
        parentFragmentManager.setFragmentResultListener(BUNDLE_KEY, this) { _, bundle ->
            val desc = bundle.getString(DESC_KEY)
            desc?.let {
                binding.etSearch.setText(it)
            }
        }

        binding.etSearch.doOnTextChanged { text, _, _, _ ->
            getUser(text.toString())

        }

        binding.btnAddUser.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddUserFragment()).addToBackStack(null).commit()
        }
    }

    private fun getUser(text: String) {
        if (text.isBlank()) {
            binding.userDesc.text = ""
            binding.btnAddUser.isVisible(false)
        } else {
            val user = UserDatabase.getUser(text)

            if (user == null) {
                binding.userDesc.text = getString(R.string.user_not_found)
                binding.btnAddUser.isVisible(true)
            } else {
                binding.btnAddUser.isVisible(false)
                println(user)
                binding.userDesc.text = user.display()
            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BUNDLE_KEY = "bundle_key"
        const val DESC_KEY = "desc_key"
    }

}


