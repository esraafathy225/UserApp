package com.example.userapp.presentation.display_user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.userapp.common.GetUserState
import com.example.userapp.databinding.FragmentGetUserBinding
import com.example.userapp.presentation.display_user.viewmodel.GetUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DisplayUserFragment : Fragment(){

    private var _binding: FragmentGetUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GetUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGetUserBinding.inflate(inflater,container,false)
        val view  = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.getUser()

        // Collecting the user state
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.addUserState.collect { state ->
                when (state) {
                    is GetUserState.Loading -> {
                        // Show loading indicator
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is GetUserState.Success -> {
                        // Hide loading indicator and update UI with user data
                        binding.progressBar.visibility = View.GONE
                        state.user.let { user ->
                            binding.tvName.text = user.name
                            binding.tvAge.text = "${user.age} years"
                            binding.tvJobTitle.text = user.jobTitle
                            binding.tvGender.text = user.gender
                        }
                    }
                    is GetUserState.Error -> {
                        // Hide loading indicator and show error message
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, state.exception.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}