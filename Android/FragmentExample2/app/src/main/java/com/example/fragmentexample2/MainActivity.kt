package com.example.fragmentexample2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fragmentexample2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),
    FragmentOne.OnFragmentOneInteractionListener,
    FragmentTwo.OnFragmentTwoInteractionListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null){
            val frag1 = FragmentOne.newInstance("String1", "String2")
            val frag2 = FragmentTwo.newInstance("String1", "String2")

            supportFragmentManager
                .beginTransaction()
                .add(R.id.theInfo, frag2, "frag2")
                .detach(frag2)
                .add(R.id.theInfo, frag1, "frag1")
                .commit()
        }//if sis == null

        binding.button.setOnClickListener {
            //we need to get the two fragments so we can detach one
            //and attach the other
            val frag1 = supportFragmentManager
                .findFragmentByTag("frag1") as FragmentOne
            val frag2 = supportFragmentManager
                .findFragmentByTag("frag2") as FragmentTwo

            //now we have fragments, we need to decide which to
            //attach/detach
            val ft = supportFragmentManager.beginTransaction()
            if (frag2.isDetached) {
                ft.detach(frag1).attach(frag2)
            }
            if (frag1.isDetached) {
                ft.detach(frag2).attach(frag1)
            }
            ft.commit()

        }//setOnClickListener

    }//onCreate

    override fun OnFragmentOneInteraction(v: View) {
        Toast.makeText(applicationContext,
        "Hello from Fragment One",
        Toast.LENGTH_LONG
        ).show()
    }//OnFragmentOneInteraction

    override fun OnFragmentTwoInteraction(v: View) {
        Toast.makeText(applicationContext,
            "Hello from Fragment Two",
            Toast.LENGTH_LONG
        ).show()
    }//OnFragmentTwoInteraction

}//MainActivity