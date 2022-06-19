package dibanez.example.info6134_group7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener


class ThirdActivity : AppCompatActivity(), OnItemSelectedListener {

    //variables for data elements
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
        println(SecondActivity.shareDogName)
    }

    override fun onResume() {
        super.onResume()

        //assign variables to corresponding views
        nameET = findViewById(R.id.editTextPostName)
        genderRG = findViewById(R.id.radioGroupGenderUpdate)
        ageSpin = findViewById(R.id.spinnerAge)
        breedSpin = findViewById(R.id.spinnerBreed)
        heightSpin = findViewById(R.id.spinnerHeight)
        lengthSpin = findViewById(R.id.spinnerLength)
        weightSpin = findViewById(R.id.spinnerWeight)

        nameET.addTextChangedListener(textWatcher)

        //setting up the spinner adapters
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

        ageSpin.onItemSelectedListener = this
        breedSpin.onItemSelectedListener = this
        heightSpin.onItemSelectedListener = this
        lengthSpin.onItemSelectedListener = this
        weightSpin.onItemSelectedListener = this
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            SecondActivity.dogDataName = s.toString()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //actions for selected options in the spinner
        when(p0?.id) {
            R.id.spinnerAge-> {
                if (p2 != 0) {
                    SecondActivity.dogDataAge = ageSpin.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataAge,Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerBreed-> {
                if (p2 != 0) {
                    SecondActivity.dogDataBreed = breedSpin.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataBreed,Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerHeight-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpin.selectedItem.toString() + " x " + lengthSpin.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions,Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerLength-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpin.selectedItem.toString() + " x " + lengthSpin.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions,Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerWeight-> {
                if (p2 != 0) {
                    SecondActivity.dogDataWeight = weightSpin.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataWeight,Toast.LENGTH_LONG).show()
                }
            }
        }
        // you can also use this command anywhere outside of the onItemSelected
        //       textView.setText(spinner.selectedItem.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Nothing Selected",Toast.LENGTH_LONG).show()
    }

    fun onRadioClicked(view: View) {
        when(view.id){
            R.id.radioButtonMaleUpdate-> {

                SecondActivity.dogDataGender = getString(R.string.male)
                Toast.makeText(this, SecondActivity.dogDataGender,Toast.LENGTH_SHORT).show()
            }
            R.id.radioButtonFemaleUpdate-> {
                SecondActivity.dogDataGender = getString(R.string.female)
                Toast.makeText(this, SecondActivity.dogDataGender,Toast.LENGTH_SHORT).show()
            }
        }

    }






}