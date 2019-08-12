package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ShareCompat
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openWebsite(v: View) {
        // get the url text
        var myUrl = website_editText.text.toString()
        // parse the uri
        var webpage = Uri.parse(myUrl)
        // create intent pass action and data of the action
        var intent = Intent(Intent.ACTION_VIEW, webpage)

        // find an activity to handle intent and start the activity
        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)

        } else {
            Log.d("ImplicitIntent", "Can't handle this intent!")
        }

    }

    fun openLocation(v: View) {
        var loc = location_editText.text.toString()
        // parse the location and create the intent
        var addressUri = Uri.parse("geo:0,0?q="+loc)
        var intent = Intent(Intent.ACTION_VIEW, addressUri)

        if(intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Log.d("ImplicitIntent", "Can't handle this intent!")
        }

    }

    fun shareText(v: View) {
        var txt = share_editText.text.toString()
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder
            .from(this)
            .setType(mimeType)
            .setChooserTitle("Share this text with: ")
            .setText(txt)
            .startChooser()
    }

}
