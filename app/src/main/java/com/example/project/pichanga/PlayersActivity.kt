package com.example.project.pichanga

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_players.*

class PlayersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)

        bind()
    }

    private fun showSimulateButton() {
        if (edittext_people.text.isNotEmpty())
            button_simulate.visibility = View.VISIBLE
        else
            button_simulate.visibility = View.INVISIBLE
    }

    private fun bind() {
        showSimulateButton()

        button_simulate.setOnClickListener { _ ->
            try {
                edittext_people.text.toString().toInt()
            }
            catch(e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val maxPeople = 3
            when {
                (edittext_people.text.toString().toInt()) >= maxPeople -> {
                    Toast.makeText(this, edittext_people.text.toString(), Toast.LENGTH_SHORT).show()
                    val simulatorIntent:Intent = SimulatorActivity.newIntent(this)
                    simulatorIntent.putExtra("people", edittext_people.text.toString().toInt())
                    startActivity(simulatorIntent)
                }
                (edittext_people.text.toString().toInt()) < maxPeople ->
                    Toast.makeText(this, "Must be more than $maxPeople", Toast.LENGTH_SHORT).show()
            }


        }

        edittext_people.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showSimulateButton()
            }
        })
    }
}
