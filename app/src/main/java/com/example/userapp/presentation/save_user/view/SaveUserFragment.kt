package com.example.userapp.presentation.save_user.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.userapp.R
import com.example.userapp.common.SaveUserState
import com.example.userapp.data.model.User
import com.example.userapp.databinding.FragmentSaveUserBinding
import com.example.userapp.presentation.save_user.viewmodel.SaveUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SaveUserFragment : Fragment() {

    private var _binding: FragmentSaveUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SaveUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSaveUserBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onValuesChanged()
        showErrors()
        enableButton()

        binding.btnSave.setOnClickListener {
            viewModel.saveUser(
                User(
                    name = binding.etName.text.toString(),
                    age = binding.etAge.text.toString(),
                    jobTitle = binding.etJobTitle.text.toString(),
                    gender = binding.etGender.text.toString()
                )
            )
            collectSaveUserState()
        }
    }

    private fun collectSaveUserState(){
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.saveUserState.collect { state ->
                when (state) {
                    is SaveUserState.Loading -> {
                        // Show loading indicator
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    is SaveUserState.Success -> {
                        // Navigate to display fragment
                        binding.progressBar.visibility=View.GONE
                        findNavController().navigate(R.id.action_inputFragment_to_displayFragment)
                    }
                    is SaveUserState.Error -> {
                        // Show error message
                        binding.progressBar.visibility=View.GONE
                        Toast.makeText(
                            context,
                            "Error: ${state.exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun onValuesChanged() {
        binding.etName.addTextChangedListener { viewModel.updateInput("name", it.toString()) }
        binding.etAge.addTextChangedListener { viewModel.updateInput("age", it.toString()) }
        binding.etJobTitle.addTextChangedListener {
            viewModel.updateInput(
                "jobTitle",
                it.toString()
            )
        }
        binding.etGender.addTextChangedListener { viewModel.updateInput("gender", it.toString()) }
    }

    private fun showErrors() {
        lifecycleScope.launch {
            viewModel.errorMessages.collect { errors ->
                binding.etName.error = errors["name"]
                binding.etAge.error = errors["age"]
                binding.etJobTitle.error = errors["jobTitle"]
                binding.etGender.error = errors["gender"]
            }
        }
    }

    private fun enableButton() {
        lifecycleScope.launch {
            viewModel.inputValid.collect { isValid ->
                binding.btnSave.isEnabled = isValid
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}