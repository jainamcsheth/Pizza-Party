package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

/**
 * Number of Slices per pizza
 */
const val SLICES_PER_PIZZA = 8

/**
 * Main class
 *
 * This is the beginning of the code and the class is referenced in AndroidManifest.xml
 */
class MainActivity : AppCompatActivity() {

    /**
     * Total number of people attending the party
     */
    private lateinit var numberOfPeople: EditText

    /**
     * Total pizzas calculated as per the number of people and hungry ratio
     */
    private lateinit var totalPizzas: TextView

    /**
     * The hungry ratio radio button - light, medium and ravenous
     */
    private lateinit var hungryRatio: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numberOfPeople = findViewById(R.id.number_of_people_edit_text)
        totalPizzas = findViewById(R.id.total_pizzas_text_view)
        hungryRatio = findViewById(R.id.hungry_ratio_radio_group)
    }

    fun calculateClick(view: View) {

        // We get the text that was typed
        val numberOfPeopleStr = numberOfPeople.text.toString()

        // Since we want to calculate, we need to convert the string to int
        val numberOfPeopleInt = numberOfPeopleStr.toInt()

        // We determine the number of slices per person as per the hungry ratio
        val totalSlicesPerPerson = when (hungryRatio.checkedRadioButtonId) {
            R.id.light_radio_button -> 2
            R.id.medium_radio_button -> 3
            else -> 4
        }

        // Final calculation for the total pizzas to be bought
        val totalPizzas = ceil(numberOfPeopleInt * totalSlicesPerPerson / SLICES_PER_PIZZA.toDouble()).toInt()
        this.totalPizzas.text = "Total pizzas: $totalPizzas"
    }
}
