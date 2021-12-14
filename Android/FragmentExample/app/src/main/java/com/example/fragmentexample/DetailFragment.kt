package com.example.fragmentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "ITEM"

class DetailFragment: Fragment() {

    private var itemName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            itemName = it.getString(ARG_PARAM1)
        }

    }//onCreate

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView = inflater.inflate(R.layout.detail_fragment,
            container, false)
        myView.findViewById<TextView>(R.id.textViewName)
            .text = itemName

        return myView

    }//onCreateView

    companion object{
        //create factory method to create new instance
        //of this fragment using provided parameters

        fun newInstance(param1: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }//DetailFragment

    }//companion object

}//DetailFragment