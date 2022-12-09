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

class BookedTicketsActivity : AppCompatActivity() {

    lateinit var homescreen : RelativeLayout
    lateinit var favAct : RelativeLayout
    lateinit var progressBar : ProgressBar
    lateinit var cardView : CardView
    lateinit var textView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_tickets)

        progressBar=findViewById(R.id.progressBar)
        cardView=findViewById(R.id.activitybookingdetails)
        textView=findViewById(R.id.activitytext)

        Handler().postDelayed({
            progressBar.visibility = View.GONE
            textView.visibility = View.VISIBLE
        }, 3000)



        homescreen=findViewById(R.id.homescreen2)
        favAct=findViewById(R.id.gotofav)

        homescreen.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        favAct.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

    }
}