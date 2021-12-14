package com.example.fragmentexample3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.fragmentexample3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener(this)

    }//onCreate

    fun addA(v: View) {
        val f1 = FragmentA()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.group,f1,"A")
            .addToBackStack("addA")
            .commit()
    }//addA

    fun addB(v: View) {
        val f2 = FragmentB()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.group,f2,"B")
            .addToBackStack("addB")
            .commit()
    }//addB

    fun removeA(v: View) {
        val f1 = supportFragmentManager.findFragmentByTag("A")
        val ft = supportFragmentManager.beginTransaction()
        if (f1 != null) {
            ft.remove(f1)
                .addToBackStack("removeA")
                .commit()
        } else {
            Toast.makeText(this,
            "Fragment A has not been added before.",
            Toast.LENGTH_LONG).show()
        }
    }//removeA

    fun removeB(v: View) {
        val f2 = supportFragmentManager.findFragmentByTag("B")
        val ft = supportFragmentManager.beginTransaction()
        if (f2 != null) {
            ft.remove(f2)
                .addToBackStack("removeB")
                .commit()
        } else {
            Toast.makeText(this,
                "Fragment B has not been added before.",
                Toast.LENGTH_LONG).show()
        }
    }//removeB

    fun replaceWithB(v: View) {
        val f2 = FragmentB()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.group,f2,"B")
            .addToBackStack("replaceWithB")
            .commit()
    }//replaceWithB

    fun replaceWithA(v: View) {
        val f1 = FragmentB()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.group,f1,"A")
            .addToBackStack("replaceWithA")
            .commit()
    }//replaceWithA

    fun attachA(v: View ){
        //gets a generic fragment, if you need the specific fragment
        //functionality, would have to cast back but check for null
        //first before casting
        val f1 = supportFragmentManager.findFragmentByTag("A")
        val ft = supportFragmentManager.beginTransaction()
        if (f1 != null) {
            ft.attach(f1)
                .addToBackStack("attachA")
                .commit()
        }
    }//attachA

    fun detachA(v: View ){
        //gets a generic fragment, if you need the specific fragment
        //functionality, would have to cast back but check for null
        //first before casting
        val f1 = supportFragmentManager.findFragmentByTag("A")
        val ft = supportFragmentManager.beginTransaction()
        if (f1 != null) {
            ft.attach(f1)
                .addToBackStack("detachA")
                .commit()
        }
    }//detachA

    fun back(v: View) {
        supportFragmentManager.popBackStack()
    }//back

    fun popAddB(v: View) {
        //remove all entries above entry name we are popping to
        supportFragmentManager.popBackStack("addB",
        FragmentManager.POP_BACK_STACK_INCLUSIVE)
            //use 0 if you want to keep the entry being popped to
    }

    override fun onBackStackChanged() {

        binding.message.text = binding.message.text.toString() + "\n\n" +
                "the current status of BackStack" + "\n"

        val count = supportFragmentManager.backStackEntryCount // 0-based indexing

        //iterate in reverse - top down
        for (i in count-1 downTo 0) {
            val entry = supportFragmentManager.getBackStackEntryAt(i)
            binding.message.text = binding.message.text.toString() + " " +
                    entry.name + "\n"
        }
        binding.message.text = binding.message.text.toString() + "\n"

    }//onBackStackChanged
}