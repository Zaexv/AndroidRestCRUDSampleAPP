package com.app.androidrestcrudsampleapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.app.androidrestcrudsampleapp.databinding.FragmentSecondBinding
import repository.Repository

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private lateinit var userViewModel: UserViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        userViewModel.getSingleUser(requireArguments().getInt("user_id"))
        userViewModel.singleUser.observe(viewLifecycleOwner, { response ->
           binding.textViewuserId.text = response.id.toString()
            binding.textViewuserName.text = response.name
            binding.textViewuserBirthday.text = response.birthDate.toString()
        })

        binding.buttonDelete.setOnClickListener {
            userViewModel.deleteUser(requireArguments().getInt("user_id"))
            userViewModel.deleteResponse.observe(viewLifecycleOwner, {response ->
                if(response.code() == 200) {
                    Toast.makeText(context,R.string.user_deleted, Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}