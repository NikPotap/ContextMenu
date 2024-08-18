package com.example.contextmenu

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var enterRatingET: EditText
    private lateinit var randomNumberBTN: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        enterRatingET = findViewById(R.id.enterRatingET)
        registerForContextMenu(enterRatingET)
        randomNumberBTN = findViewById(R.id.randomNumberBTN)
        registerForContextMenu(randomNumberBTN)
        randomNumberBTN.setOnClickListener(this)
    }

    var point = 0
    var randomNumber = 0
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        if (v?.id == R.id.enterRatingET) point = 0 else point = 1
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    @SuppressLint("ResourceAsColor")
    override fun onContextItemSelected(item: MenuItem): Boolean {
        var rate = enterRatingET.text.toString()
        when (item.itemId) {
            R.id.menu_change_color ->
                if (point == 0) {
                    enterRatingET.setTextColor(Color.rgb(0, 0, 0))
                    when (rate) {
                        "1" -> enterRatingET.setBackgroundColor(Color.rgb(0xff, 0x88, 0))
                        "2" -> enterRatingET.setBackgroundColor(Color.rgb(0xff, 0xff, 0))
                        "3" -> enterRatingET.setBackgroundColor(Color.rgb(0, 0xff, 0))
                        "4" -> enterRatingET.setBackgroundColor(Color.rgb(0, 0, 0xff))
                        "5" -> enterRatingET.setBackgroundColor(Color.rgb(0xff, 0, 0))
                        else -> {
                            enterRatingET.setBackgroundColor(Color.rgb(0xe0, 0xe0, 0xe0))
                            Toast.makeText(this, "Неверный ввод!", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    when (randomNumber) {
                        in 1..10 -> randomNumberBTN.setTextColor(Color.rgb(0xff, 0, 0))
                        in 11..20 -> randomNumberBTN.setTextColor(Color.rgb(0xff, 0x88, 0))
                        in 21..30 -> randomNumberBTN.setTextColor(Color.rgb(0xff, 0xff, 0))
                        in 31..40 -> randomNumberBTN.setTextColor(Color.rgb(0, 0xff, 0))
                        in 41..50 -> randomNumberBTN.setTextColor(Color.rgb(0, 0, 0xff))
                    }

                }

            R.id.menu_close_app -> finish()
            else -> return super.onContextItemSelected(item)
        }
        return true
    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(p0: View?) {
        randomNumber = (1..50).random()
        randomNumberBTN.setTextColor(Color.rgb(0, 0, 0))
        randomNumberBTN.text = randomNumber.toString()
    }
}