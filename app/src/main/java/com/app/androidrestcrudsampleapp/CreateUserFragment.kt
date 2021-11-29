package com.app.androidrestcrudsampleapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.androidrestcrudsampleapp.databinding.FragmentCreateUserBinding
import models.User
import repository.Repository
import java.util.*

class CreateUserFragment : Fragment() {

    private var _binding: FragmentCreateUserBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private var calendar: Calendar = Calendar.getInstance()
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private var userID = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCreateUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        binding.buttonCreateAction.setOnClickListener{
            calendar.set(
                binding.datePickerCreateBirthday.year,
                binding.datePickerCreateBirthday.month,
                binding.datePickerCreateBirthday.dayOfMonth
            )
            var birthDay = calendar.getTime()

            var name = binding.textCreateUsername.text.toString()
            var user = User(userID, name, birthDay)
            userViewModel.createUser(user)

            userViewModel.createResponse.observe(viewLifecycleOwner, { response ->
                if(response.code() == 200) {
                    Toast.makeText(context,R.string.user_created, Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                }
            })
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}