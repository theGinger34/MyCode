package com.example.fragmentfinalexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportFragmentManager.findFragmentById(R.id.content) == null) {

            supportFragmentManager.beginTransaction()
                .add(R.id.content, FirstFragment())
                .commit()
        }

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

    fun answerJoke(v: View){
        val fragment = DisplayFragment()
        val args = Bundle()
        when(v.id) {
            R.id.joke_1 -> {
                args.putInt(DisplayFragment.ARG_IMAGE_ID,
                    R.drawable.babylon)
                args.putString(DisplayFragment.ARG_TEXT_ID,
                    "The teachers Babylon.")
            }

            R.id.joke_2 -> {
                args.putInt(DisplayFragment.ARG_IMAGE_ID,
                    R.drawable.april_may)
                args.putString(DisplayFragment.ARG_TEXT_ID,
                    "No, but April May.")
            }
        }//when

        fragment.arguments = args
        supportFragmentManager.beginTransaction()
            .replace(R.id.content, fragment)
            .commit()
    }//answerJoke

}//MainActivity