package com.app.androidrestcrudsampleapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.app.androidrestcrudsampleapp.databinding.CardUserBinding
import models.User

class UserAdapter(val users : List<User>): RecyclerView.Adapter<UserAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding = CardUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.render(users[position])
    }

    override fun getItemCount(): Int {
        return users.size;
    }

    class UserHolder(val binding: CardUserBinding):RecyclerView.ViewHolder(binding.root) {
        fun render(user:User){
            binding.textUserId.text = user.id.toString()
            binding.textUserName.text = user.name
            binding.textUserBirthday.text = user.birthDate.toString()
            binding.root.setOnClickListener() {
                val bundle = bundleOf("user_id" to user.id)
                binding.root.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
            }
        }
    }
}