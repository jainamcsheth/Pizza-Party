package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

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
        val totalText = getString(R.string.total_pizzas, 0)
        totalPizzas.text = totalText
        hungryRatio = findViewById(R.id.hungry_ratio_radio_group)
    }

    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numberOfPeople.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (hungryRatio.checkedRadioButtonId) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        val totalPizzas = calc.totalPizzas

        // Place totalPizzas into the string resource and display
        val totalText = getString(R.string.total_pizzas, totalPizzas)
        this.totalPizzas.text = totalText
    }
}
