package com.app.androidrestcrudsampleapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.fragment.findNavController
import com.app.androidrestcrudsampleapp.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*
        val repository = Repository()
        val viewModelFactory = UserViewModelFactory(repository)
        userViewModel = ViewModelProvider(this,viewModelFactory).get(UserViewModel::class.java)
        userViewModel.getAllUsers()



        var u = User(4733,"String", Date())
        Log.d("User", u.toString())
        userViewModel.createUser(u)
        userViewModel.editUser(User(4733,"Petete",Date()))
        userViewModel.getSingleUser(4733)
        userViewModel.deleteUser(4733)

        userViewModel.createResponse.observe(this, { response ->
            Log.d("ResponseCreate",response.toString())
        })

        userViewModel.editResponse.observe(this, {response ->
            Log.d("ResponseEdit", response.toString())
        })

        userViewModel.deleteResponse.observe(this, {response ->
            Log.d("ResponseDelete", response.toString())
        })

        userViewModel.singleUser.observe(this, { response ->
            Log.d("GetUser", response.toString())
        })
        userViewModel.usersList.observe(this, Observer { response ->
            Log.d("Response", response.toString())
        })

*/
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}