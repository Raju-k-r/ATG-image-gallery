package com.krr.atgimagegallery

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import com.krr.atgimagegallery.resource.repository.Repository
import com.krr.atgimagegallery.ui.home.HomeViewModel
import com.krr.atgimagegallery.ui.home.HomeViewModelFactory
import com.krr.atgimagegallery.ui.home.ImageAdaptor

class MainActivity : AppCompatActivity() {

    private lateinit var homeViewModel : HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar : Toolbar = findViewById(R.id.toolbar)
        toolbar.title = "ATG Gallery"

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)

        val navigationView : NavigationView = findViewById(R.id.nav_view)

        val actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        Log.d("Response", "Creating the Instance")
        val repository = Repository()

        val homeViewModelFactory = HomeViewModelFactory(repository)

        val progressBar : ProgressBar = findViewById(R.id.progressBar)

        homeViewModel = ViewModelProvider(this,homeViewModelFactory).get(HomeViewModel::class.java)

        homeViewModel.getAllImages()

        recyclerView = findViewById(R.id.image_recycler_view)

        homeViewModel.images.observe(this, Observer { response->
            if(response.isNotEmpty()){
                progressBar.visibility = View.GONE
            }else{
                progressBar.visibility = View.VISIBLE
            }
            if(recyclerView.adapter == null){
                recyclerView.adapter = ImageAdaptor(response)
            }else{
                (recyclerView.adapter as ImageAdaptor).updateAdaptor(response)
            }
        })

        navigationView.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId) {
                    R.id.nav_home -> drawerLayout.closeDrawer(GravityCompat.START)
                }
                return true
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

}