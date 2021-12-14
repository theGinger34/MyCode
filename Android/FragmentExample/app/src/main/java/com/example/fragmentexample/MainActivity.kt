package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.fragmentexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var myListFragment: MyListFragment? = null
    private var fragmentTwo: FragmentTwo? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.listButton.setOnClickListener{
            loadFragment("List")
        }

        binding.fragmentTwoButton.setOnClickListener{
            loadFragment("Two")
        }

        //would need to keep track of which fragment is being displayed
        //for rotation to continue to be displayed
        //we are just going to let the fragment being displayed
        //to be reloaded
        loadFragment("List")
    }//onCreate

        private fun loadFragment(which: String){

            if (which == "List") {

                //should check if null first if we want to use
                //the same instance
                myListFragment = MyListFragment()

                //set the change listener - define below
                myListFragment?.setMyItemChangedListener(itemChangedListener)

                //note: no transition or backstack - but clear backstack
                supportFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.theInfo,myListFragment!!)
                    .commit()

            } else if (which == "Two") {
                //should check if null first if we want to use
                //the same instance
                fragmentTwo = FragmentTwo()


                //note: no transition or backstack - but clear backstack
                supportFragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.theInfo,fragmentTwo!!)
                    .commit()
            }



        }//loadFragment

        //listener for the list fragment
    private var itemChangedListener: MyListFragment.ItemChangeListener =
        object : MyListFragment.ItemChangeListener {
            override fun onSelectedItemChanged(itemNameString: String) {

                //create and show the detail fragment
                val details = DetailFragment.newInstance(itemNameString)

                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.animator.fragment_animation_fade_in,
                        R.animator.fragment_animation_fade_out)
                    .replace(R.id.theInfo, details)
                    .addToBackStack(null)
                    .commit()
            }//onSelectedItemChanged

        }//itemChangedListener

}//MainActivity