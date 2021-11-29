package com.app.androidrestcrudsampleapp

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.app.androidrestcrudsampleapp.databinding.FragmentSecondBinding
import repository.Repository
import utils.formatToViewDateTimeDefaults

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
            binding.textViewuserBirthday.text = response.birthDate.formatToViewDateTimeDefaults()


        })

        binding.buttonDelete.setOnClickListener {
            showDeleteDialog()
        }
        
        binding.buttonEdit.setOnClickListener {
            val bundle = bundleOf("id" to userViewModel.singleUser.value?.id,
            "name" to userViewModel.singleUser.value?.name,
            "birthday" to userViewModel.singleUser.value?.birthDate
            )

            findNavController().navigate(R.id.action_edit_user,bundle)
        }
    }

    private fun showDeleteDialog() {
        lateinit var dialog: AlertDialog

        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle(getText(R.string.delete_user))
        builder.setMessage(R.string.delete_user_message)

        val dialogClickListener = DialogInterface.OnClickListener { _, which ->
            when (which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    userViewModel.deleteUser(requireArguments().getInt("user_id"))
                    userViewModel.deleteResponse.observe(viewLifecycleOwner, {response ->
                        if(response.code() == 200) {
                            Toast.makeText(context,R.string.user_deleted, Toast.LENGTH_SHORT).show()
                            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
                        }
                    })
                }
            }
        }

        builder.setPositiveButton(getText(R.string.yes), dialogClickListener)
        builder.setNeutralButton(getText(R.string.cancel), dialogClickListener)

        dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}