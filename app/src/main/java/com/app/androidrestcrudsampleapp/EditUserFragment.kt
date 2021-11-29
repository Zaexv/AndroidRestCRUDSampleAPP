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
import com.app.androidrestcrudsampleapp.databinding.FragmentEditUserBinding
import models.User
import repository.Repository
import java.time.Year
import java.util.*

class EditUserFragment : Fragment() {

    private var _binding: FragmentEditUserBinding? = null
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
        _binding = FragmentEditUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Edit-user","fragmento creato")

        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)

        val bundle: Bundle = requireArguments()
        binding.textEditUsername.setText(bundle.getString("name"))
        userID = bundle.getInt("id")
        var birthDay: Date = bundle.get("birthday") as Date

        calendar.setTime(birthDay)
        binding.datePickerBirthday.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        binding.buttonEditAction.setOnClickListener{
            calendar.set(binding.datePickerBirthday.year,
                binding.datePickerBirthday.month,
                binding.datePickerBirthday.dayOfMonth)
            birthDay = calendar.getTime()

            var name = binding.textEditUsername.text.toString()
            var user = User(userID, name, birthDay)
            userViewModel.editUser(user)

            userViewModel.editResponse.observe(viewLifecycleOwner, { response ->
                if(response.code() == 200) {
                    Toast.makeText(context,R.string.user_edited, Toast.LENGTH_SHORT).show()
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