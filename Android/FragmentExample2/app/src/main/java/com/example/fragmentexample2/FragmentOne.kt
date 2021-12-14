package com.example.fragmentexample2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.lang.RuntimeException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentOne.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentOne : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //to communicate back to hosting activity
    private var listener: OnFragmentOneInteractionListener? = null

    interface OnFragmentOneInteractionListener {
        fun OnFragmentOneInteraction(v: View)
    }//OnFragmentOneInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentOneInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() +
            " must implement OnFragmentOneInteractionListener")
        }
    }//onAttach

    override fun onDetach() {
        super.onDetach()
        listener = null
    }//onDetach

    //end of code needed to communicate back to activity - other than
    //call the listener method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_one, container, false)

        val bt = v.findViewById<Button>(R.id.button)

        bt.setOnClickListener{ onButtonPressed(bt) }

        return v
    }//onCreateView

    fun onButtonPressed(v: View){
        listener?.OnFragmentOneInteraction(v)
    }//onButtonPressed

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentOne.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentOne().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}