package com.example.finalexamschweickhardt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Switch
import com.example.finalexamschweickhardt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val commit = supportFragmentManager.beginTransaction()
            .add(R.id.theInfo, TempFragment(), null)
            .commit()


    }//onCreate

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }//onCreateOptionsMenu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menue_info -> InfoDialog()
                .show(supportFragmentManager, "info")
        }
        return super.onOptionsItemSelected(item)
    }//onOptionsItemSelected



}//MainActivity