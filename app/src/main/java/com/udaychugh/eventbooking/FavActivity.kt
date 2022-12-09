package com.udaychugh.eventbooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView

class FavActivity : AppCompatActivity() {

    lateinit var homescreen : RelativeLayout
    lateinit var bookAct : RelativeLayout
    lateinit var progressBar : ProgressBar
    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        homescreen=findViewById(R.id.homescreen)
        bookAct=findViewById(R.id.gotobookedtickets)

        progressBar=findViewById(R.id.progressBar2)
        textView=findViewById(R.id.textofinfo)

        Handler().postDelayed({
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }, 3000)

        homescreen.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        bookAct.setOnClickListener {
            val intent = Intent(this, BookedTicketsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }
    }
}