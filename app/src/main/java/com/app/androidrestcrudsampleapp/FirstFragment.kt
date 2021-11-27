package com.app.androidrestcrudsampleapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.androidrestcrudsampleapp.databinding.FragmentFirstBinding
import models.User
import repository.Repository
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var userViewModel: UserViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)
            //var u = User(0,"FragmenTest", Date())
            //Log.d("User", u.toString())
            userViewModel.getAllUsers()

            userViewModel.usersList.observe(viewLifecycleOwner, { response ->
                Log.d("ResponseList",response.toString())
                binding.textviewFirst.text = response.toString()
            })


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}