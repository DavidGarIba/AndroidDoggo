package dibanez.example.info6134_group7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class ThirdActivity : AppCompatActivity() {

    lateinit var nameET: EditText
    lateinit var genderRG: RadioGroup

    lateinit var ageSpin: Spinner
    lateinit var breedSpin: Spinner
    lateinit var heightSpin: Spinner
    lateinit var lengthSpin: Spinner
    lateinit var weightSpin: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    override fun onResume() {
        super.onResume()

        nameET = findViewById(R.id.editTextPostName)
        genderRG = findViewById(R.id.radioGroupGender)
        ageSpin = findViewById(R.id.spinnerAge)
        breedSpin = findViewById(R.id.spinnerBreed)
        heightSpin = findViewById(R.id.spinnerHeight)
        lengthSpin = findViewById(R.id.spinnerLength)
        weightSpin = findViewById(R.id.spinnerWeight)

        val ageSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.ageArray,
            android.R.layout.simple_spinner_item
        )
        val breedSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.breedArray,
            android.R.layout.simple_spinner_item
        )
        val heightSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.heightArray,
            android.R.layout.simple_spinner_item
        )
        val lengthSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.lengthArray,
            android.R.layout.simple_spinner_item
        )
        val weightSpinnerAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.weightArray,
            android.R.layout.simple_spinner_item
        )

        ageSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        breedSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lengthSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        ageSpin.adapter = ageSpinnerAdapter
        breedSpin.adapter = breedSpinnerAdapter
        heightSpin.adapter = heightSpinnerAdapter
        lengthSpin.adapter = lengthSpinnerAdapter
        weightSpin.adapter = weightSpinnerAdapter


//        ageSpin.onItemSelectedListener = this
    }




}