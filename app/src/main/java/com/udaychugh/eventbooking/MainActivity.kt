package com.udaychugh.eventbooking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import androidx.transition.Fade
import androidx.transition.Slide
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    lateinit var fav : RelativeLayout
    lateinit var bookedTickets : RelativeLayout

    val comdeyshowimage1 = "https://github.com/udaychugh/kalam/blob/main/database/the_Image.jpg?raw=true"
    val comdeyshowimage2 = "https://github.com/udaychugh/kalam/blob/main/database/comedy2.jpg?raw=true"
    val comdeyshowimage3 = "https://github.com/udaychugh/kalam/blob/main/database/comedy3.jpg?raw=true"
    val playimage1 = "https://github.com/udaychugh/kalam/blob/main/database/playimage1.png?raw=true"
    val playimage2 = "https://github.com/udaychugh/kalam/blob/main/database/playimage2.png?raw=true"
    val playimage3 = "https://github.com/udaychugh/kalam/blob/main/database/playimage3.png?raw=true"
    val movieimage1 = "https://github.com/udaychugh/kalam/blob/main/database/movie1.jpg?raw=true"
    val movieimage2 = "https://github.com/udaychugh/kalam/blob/main/database/movie2.png?raw=true"

    lateinit var scrollView: NestedScrollView
    lateinit var filter : ImageView
    lateinit var bottomNav : LinearLayout

    //cardviews
    lateinit var card1 : CardView
    lateinit var card2 : CardView
    lateinit var card3 : CardView
    lateinit var card4 : CardView
    lateinit var card5 : CardView
    lateinit var card6 : CardView
    lateinit var card7 : CardView
    lateinit var card8 : CardView

    //linear layouts of card
    lateinit var comedycard1 : LinearLayout
    lateinit var comedycard2 : LinearLayout
    lateinit var comedycard3 : LinearLayout
    lateinit var playcard1 : LinearLayout
    lateinit var playcard2 : LinearLayout
    lateinit var playcard3 : LinearLayout
    lateinit var moviecard1 : LinearLayout
    lateinit var moviecard2 : LinearLayout

    //buttons of booking
    lateinit var comedybtn1 : AppCompatButton
    lateinit var comedybtn2 : AppCompatButton
    lateinit var comedybtn3 : AppCompatButton
    lateinit var playbtn1 : AppCompatButton
    lateinit var playbtn2 : AppCompatButton
    lateinit var playbtn3 : AppCompatButton
    lateinit var moviebtn1 : AppCompatButton
    lateinit var moviebtn2 : AppCompatButton

    //image of cardview
    lateinit var comedyimg1 : ImageView
    lateinit var comedyimg2 : ImageView
    lateinit var comedyimg3 : ImageView
    lateinit var playimg1 : ImageView
    lateinit var playimg2 : ImageView
    lateinit var playimg3 : ImageView
    lateinit var movieimg1 : ImageView
    lateinit var movieimg2 : ImageView

    //title of cardview
    lateinit var comedytitle1 : TextView
    lateinit var comedytitle2 : TextView
    lateinit var comedytitle3 : TextView
    lateinit var playtitle1 : TextView
    lateinit var playtitle2 : TextView
    lateinit var playtitle3 : TextView
    lateinit var movietitle1 : TextView
    lateinit var movietitle2 : TextView

    //desc of cardview
    lateinit var comedydesc1 : TextView
    lateinit var comedydesc2 : TextView
    lateinit var comedydesc3 : TextView
    lateinit var playdesc1 : TextView
    lateinit var playdesc2 : TextView
    lateinit var playdesc3 : TextView
    lateinit var moviedesc1 : TextView
    lateinit var moviedesc2 : TextView


    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addIdintoVar()
        setRandomCardBG()
        addImagesAndText()

        fav.setOnClickListener {
            val intent = Intent(this, FavActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
            finish()
        }

        bookedTickets.setOnClickListener {
            val intent = Intent(this, BookedTicketsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
            finish()
        }

        //hiding bottom menu
        val state = IntArray(1)
        scrollView.setOnScrollChangeListener(object : NestedScrollView.OnScrollChangeListener {
            override fun onScrollChange(
                v: NestedScrollView,
                scrollX: Int,
                scrollY: Int,
                oldScrollX: Int,
                oldScrollY: Int
            ) {
                //adding animation in hiding or showing bottom navbar
                val transition: Transition = Slide(Gravity.BOTTOM)
                transition.duration = 200
                transition.addTarget(R.id.bottomNavBar)
                TransitionManager.beginDelayedTransition(bottomNav, transition)

                if (scrollY == 0)
                {
                    showToolbar()
                }
                else{
                    hideToolbar()
                }
            }

        })

        comedybtn1.setOnClickListener {
            openDialogOfbokking("comdeyshowimage1", "Da Komedi Show", "8", "399")
        }

        comedybtn2.setOnClickListener {
            openDialogOfbokking("comdeyshowimage2", "Comedy Show", "9", "499")
        }

        comedybtn3.setOnClickListener {
            openDialogOfbokking("comdeyshowimage3", "Open Mic", "10", "599")
        }

        playbtn1.setOnClickListener {
            openDialogOfbokking("playimage1", "Hauntings", "11", "699")
        }

        playbtn2.setOnClickListener {
            openDialogOfbokking("playimage3", "Siblings", "12", "799")
        }

        playbtn3.setOnClickListener {
            openDialogOfbokking("playimage3", "Ka Ka", "1", "899")
        }

        moviebtn1.setOnClickListener {
            openDialogOfbokking("movieimage1", "Drishyam", "2", "999")
        }

        moviebtn2.setOnClickListener {
            openDialogOfbokking("movieimage2", "Bhedhiya", "3", "1099")
        }

        //showing bottom sheet modal
        var filterValue : Int = 0
        filter.setOnClickListener {



            if (filterValue > 0)
            {
                changeFilterToNormal()
                filterValue = 0
            }
            else{
                val dialog = BottomSheetDialog(this)
                val view = layoutInflater.inflate(R.layout.filer_bottom_sheet_layout, null)
                val addFilter = view.findViewById<AppCompatButton>(R.id.addingfilter)
                val comedyFilter = view.findViewById<LinearLayout>(R.id.comedyfilter)
                val playFilter = view.findViewById<LinearLayout>(R.id.playfilter)
                val movieFilter = view.findViewById<LinearLayout>(R.id.moviefilter)
                val recentFilter = view.findViewById<LinearLayout>(R.id.recentfilter)



                comedyFilter.setOnClickListener {
                    filterValue = 1
                    comedyFilter.background = (ContextCompat.getDrawable(this, R.color.app_orange))
                }

                playFilter.setOnClickListener {
                    filterValue = 2
                    playFilter.background = (ContextCompat.getDrawable(this, R.color.app_orange))
                }

                movieFilter.setOnClickListener {
                    filterValue = 3
                    movieFilter.background = (ContextCompat.getDrawable(this, R.color.app_orange))
                }

                recentFilter.setOnClickListener {
                    filterValue = 4
                    recentFilter.background = (ContextCompat.getDrawable(this, R.color.app_orange))
                }

                addFilter.setOnClickListener {
                    when (filterValue) {
                        0 -> {
                            Toast.makeText(applicationContext, "No Filter is Selected", Toast.LENGTH_SHORT).show()
                        }
                        1 -> {
                            Toast.makeText(applicationContext, "Comedy Filter Added", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            applyFilter(1)
                            changeFilterIcon()
                        }
                        2 -> {
                            Toast.makeText(applicationContext, "Play Filter Added", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            applyFilter(2)
                            changeFilterIcon()
                        }
                        3 -> {
                            Toast.makeText(applicationContext, "Movie Filter Added", Toast.LENGTH_SHORT).show()
                            dialog.dismiss()
                            applyFilter(3)
                            changeFilterIcon()
                        }
                        4 -> {
                            Toast.makeText(applicationContext, "No Recent Booking was done", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(applicationContext, "An Error Occurred", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

                dialog.setCancelable(true)
                dialog.setContentView(view)
                dialog.show()
            }

        }

    }

    private fun applyFilter(num: Int) {
        val tr : Transition = Slide(Gravity.BOTTOM)
        tr.duration = 300
        tr.addTarget(R.id.cardview1)
        TransitionManager.beginDelayedTransition(card1, tr)
        tr.addTarget(R.id.cardview2)
        TransitionManager.beginDelayedTransition(card2, tr)
        tr.addTarget(R.id.cardview3)
        TransitionManager.beginDelayedTransition(card3, tr)
        tr.addTarget(R.id.cardview4)
        TransitionManager.beginDelayedTransition(card4, tr)
        tr.addTarget(R.id.cardview5)
        TransitionManager.beginDelayedTransition(card5, tr)
        tr.addTarget(R.id.cardview6)
        TransitionManager.beginDelayedTransition(card6, tr)
        tr.addTarget(R.id.cardview7)
        TransitionManager.beginDelayedTransition(card7, tr)
        tr.addTarget(R.id.cardview8)
        TransitionManager.beginDelayedTransition(card8, tr)
        when (num)
        {
            1 -> {
                card4.visibility = View.GONE
                card5.visibility = View.GONE
                card6.visibility = View.GONE
                card7.visibility = View.GONE
                card8.visibility = View.GONE
            }
            2 -> {
                card1.visibility = View.GONE
                card2.visibility = View.GONE
                card3.visibility = View.GONE
                card7.visibility = View.GONE
                card8.visibility = View.GONE
            }
            3 -> {
                card4.visibility = View.GONE
                card5.visibility = View.GONE
                card6.visibility = View.GONE
                card1.visibility = View.GONE
                card2.visibility = View.GONE
                card3.visibility = View.GONE
            }
            else -> {
                Toast.makeText(applicationContext, "An Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun changeFilterToNormal() {
        Glide.with(this).load(R.drawable.filter_off).into(filter)
        filter.background = (ContextCompat.getDrawable(this, R.color.white))
        card1.visibility = View.VISIBLE
        card2.visibility = View.VISIBLE
        card3.visibility = View.VISIBLE
        card4.visibility = View.VISIBLE
        card5.visibility = View.VISIBLE
        card6.visibility = View.VISIBLE
        card7.visibility = View.VISIBLE
        card8.visibility = View.VISIBLE
    }

    private fun changeFilterIcon() {
        Glide.with(this).load(R.drawable.filter_on).into(filter)
        filter.background = (ContextCompat.getDrawable(this, R.color.app_orange))

    }

    @SuppressLint("InflateParams")
    private fun openDialogOfbokking(showImage: String, showName: String, showTime: String, showPrice: String) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.show_bottom_sheet_layout, null)
        val confirmBtn = view.findViewById<AppCompatButton>(R.id.confirmbtn)
        val imageDisplay = view.findViewById<ImageView>(R.id.showingshowimagehereonly)
        val titleDisplay = view.findViewById<TextView>(R.id.entertitlehere)
        val timeDisplay = view.findViewById<TextView>(R.id.entertimehere)
        val priceDisplay = view.findViewById<TextView>(R.id.enterpricehere)
        val incQty = view.findViewById<AppCompatButton>(R.id.plus)
        val decQty = view.findViewById<AppCompatButton>(R.id.minus)
        val qtyDisplay = view.findViewById<TextView>(R.id.showingqtyhereonly)

        var quantity : Int = 1

        when (showImage)
        {
            "comdeyshowimage1" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/the_Image.jpg?raw=true").into(imageDisplay)
            }
            "comdeyshowimage2" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/comedy2.jpg?raw=true").into(imageDisplay)
            }
            "comdeyshowimage3" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/comedy3.jpg?raw=true").into(imageDisplay)
            }
            "playimage1" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/playimage1.png?raw=true").into(imageDisplay)
            }
            "playimage2" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/playimage2.png?raw=true").into(imageDisplay)
            }
            "playimage3" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/playimage3.png?raw=true").into(imageDisplay)
            }
            "movieimage1" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/movie1.jpg?raw=true").into(imageDisplay)
            }
            "movieimage2" -> {
                Glide.with(this).load("https://github.com/udaychugh/kalam/blob/main/database/movie2.png?raw=true").into(imageDisplay)
            }
        }



        titleDisplay.text = showName
        timeDisplay.text = showTime
        qtyDisplay.text = quantity.toString()
        priceDisplay.text = (quantity * showPrice.toInt()).toString()

        incQty.setOnClickListener {
            quantity++
            qtyDisplay.text = quantity.toString()
            priceDisplay.text = (quantity * showPrice.toInt()).toString()
        }

        decQty.setOnClickListener {

            if (quantity == 1)
            {
                Toast.makeText(applicationContext, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show()
            }
            else
            {
                quantity--
                qtyDisplay.text = quantity.toString()
                priceDisplay.text = (quantity * showPrice.toInt()).toString()
            }

        }

        confirmBtn.setOnClickListener {
            Toast.makeText(applicationContext, "buton Clicked", Toast.LENGTH_SHORT).show()
        }

        dialog.setCancelable(true)
        dialog.setContentView(view)
        dialog.show()
    }

    @SuppressLint("SetTextI18n")
    private fun addImagesAndText() {


        Glide.with(this).load(comdeyshowimage1).into(comedyimg1)
        Glide.with(this).load(comdeyshowimage2).into(comedyimg2)
        Glide.with(this).load(comdeyshowimage3).into(comedyimg3)
        Glide.with(this).load(playimage1).into(playimg1)
        Glide.with(this).load(playimage2).into(playimg2)
        Glide.with(this).load(playimage3).into(playimg3)
        Glide.with(this).load(movieimage1).into(movieimg1)
        Glide.with(this).load(movieimage2).into(movieimg2)

        comedytitle1.text = "Da Komedi Show"
        comedytitle2.text = "Comedy Show"
        comedytitle3.text = "Open Mic"
        playtitle1.text = "Haunting"
        playtitle2.text = "Siblings"
        playtitle3.text = "Ka Ka"
        movietitle1.text = "Drishaym"
        movietitle2.text = "Bhediya"

        comedydesc1.text = "8:00 AM\n" +
                "₹ 399"
        comedydesc2.text = "9:00 AM\n" +
                "₹ 499"
        comedydesc3.text = "10:00 AM\n" +
                "₹ 599"
        playdesc1.text = "11:00 AM\n" +
                "₹ 699"
        playdesc2.text = "12:00 PM\n" +
                "₹ 799"
        playdesc3.text = "1:00 PM\n" +
                "₹ 899"
        moviedesc1.text = "2:00 PM\n" +
                "₹ 999"
        moviedesc2.text = "3:00 PM\n" +
                "₹ 1099"


    }

    private fun setRandomCardBG() {
        try {
            val rnds = (1..4).random()
            val cardBG : String = "R.drawable.cardview_bg$rnds"
            comedycard1.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            comedycard2.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            comedycard3.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            playcard1.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            playcard2.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            playcard3.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            moviecard1.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
            moviecard2.background = (ContextCompat.getDrawable(this, cardBG.toInt()))
        }catch (e : Exception)
        {
            println(e)
        }
        finally {
            comedycard1.background = (ContextCompat.getDrawable(this, R.drawable.cardview_bg4))
        }


    }

    private fun addIdintoVar() {

        fav = findViewById(R.id.favshows)
        bookedTickets = findViewById(R.id.bookedtickets)

        scrollView = findViewById(R.id.scrollView)
        filter = findViewById(R.id.filteraddon)
        bottomNav = findViewById(R.id.bottomNavBar)

        //cards
        card1 = findViewById(R.id.cardview1)
        card2 = findViewById(R.id.cardview2)
        card3 = findViewById(R.id.cardview3)
        card4 = findViewById(R.id.cardview4)
        card5 = findViewById(R.id.cardview5)
        card6 = findViewById(R.id.cardview6)
        card7 = findViewById(R.id.cardview7)
        card8 = findViewById(R.id.cardview8)

        //linearlayouts
        comedycard1 = findViewById(R.id.comedyshow1)
        comedycard2 = findViewById(R.id.comedyshow2)
        comedycard3 = findViewById(R.id.comedyshow3)
        playcard1 = findViewById(R.id.play1)
        playcard2 = findViewById(R.id.play2)
        playcard3 = findViewById(R.id.play3)
        moviecard1 = findViewById(R.id.movie1)
        moviecard2 = findViewById(R.id.movie2)

        //buttons
        comedybtn1 = findViewById(R.id.comedyshowbtn1)
        comedybtn2 = findViewById(R.id.comedyshowbtn2)
        comedybtn3 = findViewById(R.id.comedyshowbtn3)
        playbtn1 = findViewById(R.id.playbtn1)
        playbtn2 = findViewById(R.id.playbtn2)
        playbtn3 = findViewById(R.id.playbtn3)
        moviebtn1 = findViewById(R.id.moviebtn1)
        moviebtn2 = findViewById(R.id.moviebtn2)

        //title of cardview
        comedytitle1 = findViewById(R.id.comedyshowtitle1)
        comedytitle2 = findViewById(R.id.comedyshowtitle2)
        comedytitle3 = findViewById(R.id.comedyshowtitle3)
        playtitle1 = findViewById(R.id.playtitle1)
        playtitle2 = findViewById(R.id.playtitle2)
        playtitle3 = findViewById(R.id.playtitle3)
        movietitle1 = findViewById(R.id.movietitle1)
        movietitle2 = findViewById(R.id.movietitle2)

        //desc of cardview
        comedydesc1 = findViewById(R.id.comedyshowdesc1)
        comedydesc2 = findViewById(R.id.comedyshowdesc2)
        comedydesc3 = findViewById(R.id.comedyshowdesc3)
        playdesc1 = findViewById(R.id.playdesc1)
        playdesc2 = findViewById(R.id.playdesc2)
        playdesc3 = findViewById(R.id.playdesc3)
        moviedesc1 = findViewById(R.id.moviedesc1)
        moviedesc2 = findViewById(R.id.moviedesc2)

        //image
        comedyimg1 = findViewById(R.id.comedyshowimg1)
        comedyimg2 = findViewById(R.id.comedyshowimg2)
        comedyimg3 = findViewById(R.id.comedyshowimg3)
        playimg1 = findViewById(R.id.playimg1)
        playimg2 = findViewById(R.id.playimg2)
        playimg3 = findViewById(R.id.playimg3)
        movieimg1 = findViewById(R.id.movieimg1)
        movieimg2 = findViewById(R.id.movieimg2)


    }

    private fun hideToolbar() {
        bottomNav.visibility = View.GONE
    }

    private fun showToolbar() {
        bottomNav.visibility = View.VISIBLE
    }
}