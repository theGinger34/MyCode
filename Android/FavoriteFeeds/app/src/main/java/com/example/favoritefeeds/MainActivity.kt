package com.example.favoritefeeds

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.favoritefeeds.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //SharedPreferences containing users saved feeds
    private lateinit var savedFeeds: SharedPreferences

    private lateinit var binding: ActivityMainBinding

    private var isEdit = false
    private lateinit var old: ConstraintLayout
    private var oldTag = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedFeeds = getSharedPreferences("feeds", Context.MODE_PRIVATE)

        //register listeners for save and clear tags buttons
        binding.saveButton.setOnClickListener { handelSaveButtonClick() }
        binding.clearTagsButton.setOnClickListener { handelClearTagsButtonClick() }

        refreshButtons(null) //add previously saved feeds to GUI
    }//onCreate

    //if newTag == null, add all saved buttons
    //if newTag !=null, just add buttons being saved
    private fun refreshButtons(newTag: String?){

        //stored saved tags in array
        val tags: Array<String> = savedFeeds.all.keys.toTypedArray()
        tags.sortWith(String.CASE_INSENSITIVE_ORDER) //sort by tag
        //if a new tag is added, insert in GUI at the appropriate location
        if (newTag != null){

            var index = tags.indexOf(newTag)
            makeTagGUI(newTag,index)

        } else {
            //display all tags
            for (index in tags.indices) {
                makeTagGUI(tags[index], index)
            }
        }

    }//refreshButtons

    //add new tag/query to the shared preferences and then refresh buttons
    private fun makeTag(query: String, tag: String) {

        //originalQuery will be "" if we have new item
        val originalQuery = savedFeeds.getString(tag, "")

        //get sharedPreferences Editor to store new or edit existing
        //tag/query pairs
        val editor = savedFeeds.edit()

        if (isEdit){
            editor.remove(oldTag).commit()
        }

        editor.putString(tag,query)//store current values
        editor.apply()//update the fire

        //or
        //savedFeeds.edit().putString(tag,query).apply()

        //if new query, add its GUI
        if (originalQuery == "") {
            refreshButtons(tag)
        }
        if (oldTag != tag && isEdit) {
            binding.queryLinearLayout.removeView(old)
        }

    }//makeTag

    //add a new button to the GUI
    private fun makeTagGUI(tag: String, index: Int){

        //inflate new_tag_view.xml file
        val newTagView = layoutInflater.inflate(R.layout.new_tag_view, null, false)

        //get newTagButton, set its text and register its listener
        val newTagButton = newTagView.findViewById<Button>(R.id.newTagButton)
        newTagButton.text = tag
        newTagButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v:View?) {
                handelQueryButtonClicked(v as Button)
            }
        })

        //get newEditButton and register its listener
        val newEditButton = newTagView.findViewById<Button>(R.id.newEditButton)
        newEditButton.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                handelEditButtonClicked(v as Button)
            }
        })

        //add the two buttons to liner layout
        binding.queryLinearLayout.addView(newTagView, index)

    }//makeTagGUI

    //removed all saved buttons from GUI
    private fun clearButtons() {
        binding.queryLinearLayout.removeAllViews()
    }//clearButtons

    private fun handelSaveButtonClick() {

        //create tag if both query and tage edit text are not empty
        if (binding.queryEditText.text.isNotEmpty() &&
                binding.tagEditText.text.isNotEmpty()) {

            makeTag(binding.queryEditText.text.toString(),
                binding.tagEditText.text.toString())

            binding.queryEditText.setText("")
            binding.tagEditText.setText("")

            //hide keyboard
            (getSystemService(Activity.INPUT_METHOD_SERVICE) as
                    InputMethodManager)
                .hideSoftInputFromWindow(binding.tagEditText.windowToken, 0)
            isEdit = false
        } else {
            //display error message

            //create an AlertDialog Builder
            var builder = AlertDialog.Builder(this@MainActivity)

            builder.setTitle(R.string.missingTitle)

            //provide OK button to dissmis dialog
            builder.setPositiveButton(R.string.OK, null)

            builder.setMessage(R.string.missingMessage)

            //create the actual dialog
            var errorDialog = builder.create()
            errorDialog.show()

        }

    }//handelSaveButtonClick

    private fun handelClearTagsButtonClick() {

        var builder = AlertDialog.Builder(this@MainActivity)

        builder.setTitle(R.string.confirmTitle)

        builder.setPositiveButton(R.string.erase) {
         dialog, which ->

         clearButtons()
         savedFeeds.edit().clear().apply()
        }

        builder.setCancelable(true)
        builder.setNegativeButton(R.string.cancel, null)

        builder.setMessage(R.string.confirmMessage)

        builder.create().show()

    }//handelClearTagsButtonClick

    //load selected search/rss feed in browser
    private fun handelQueryButtonClicked(v: Button){

        //get query
        val buttonText = v.text.toString()
        val query = savedFeeds.getString(buttonText, "")

        //create URL
        val urlString = getString(R.string.searchURL) + query

        //create Intent to launch browser
        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urlString))

        //execute Intent
        startActivity(webIntent)

    }//handelQueryButtonClicked

    private fun handelEditButtonClicked(v: Button) {

        //Get necessary GUI components
        val buttonRow = v.parent as ConstraintLayout

        val searchButton = buttonRow.findViewById<Button>(R.id.newTagButton)

        val tag = searchButton.text.toString()

        //set edit texts to match
        binding.tagEditText.setText(tag)
        binding.queryEditText.setText(savedFeeds.getString(tag,""))

        isEdit = true
        old = buttonRow
        oldTag = tag

    }//handelEditButtonClicked

}//mainActivity