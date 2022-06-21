package dibanez.example.info6134_group7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener


class UpdateActivity : AppCompatActivity(), OnItemSelectedListener {

    //variables for data elements
    lateinit var nameETUpdate: EditText
    lateinit var genderRGUpdate: RadioGroup

    lateinit var ageSpinUpdate: Spinner
    lateinit var breedSpinUpdate: Spinner
    lateinit var heightSpinUpdate: Spinner
    lateinit var lengthSpinUpdate: Spinner
    lateinit var weightSpinUpdate: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
    }

    override fun onResume() {
        super.onResume()

        //assign variables to corresponding views
        nameETUpdate = findViewById(R.id.editTextNameUpdate)
        genderRGUpdate = findViewById(R.id.radioGroupGenderUpdate)
        ageSpinUpdate = findViewById(R.id.spinnerAgeUpdate)
        breedSpinUpdate = findViewById(R.id.spinnerBreedUpdate)
        heightSpinUpdate = findViewById(R.id.spinnerHeightUpdate)
        lengthSpinUpdate = findViewById(R.id.spinnerLengthUpdate)
        weightSpinUpdate = findViewById(R.id.spinnerWeightUpdate)

        nameETUpdate.addTextChangedListener(textWatcher)

        //setting up the spinner adapters
        val ageSpinnerAdapterUpdate = ArrayAdapter.createFromResource(
            this,
            R.array.ageArray,
            android.R.layout.simple_spinner_item
        )
        val breedSpinnerAdapterUpdate = ArrayAdapter.createFromResource(
            this,
            R.array.breedArray,
            android.R.layout.simple_spinner_item
        )
        val heightSpinnerAdapterUpdate = ArrayAdapter.createFromResource(
            this,
            R.array.heightArray,
            android.R.layout.simple_spinner_item
        )
        val lengthSpinnerAdapterUpdate = ArrayAdapter.createFromResource(
            this,
            R.array.lengthArray,
            android.R.layout.simple_spinner_item
        )
        val weightSpinnerAdapterUpdate = ArrayAdapter.createFromResource(
            this,
            R.array.weightArray,
            android.R.layout.simple_spinner_item
        )

        ageSpinnerAdapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        breedSpinnerAdapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightSpinnerAdapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lengthSpinnerAdapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinnerAdapterUpdate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        ageSpinUpdate.adapter = ageSpinnerAdapterUpdate
        breedSpinUpdate.adapter = breedSpinnerAdapterUpdate
        heightSpinUpdate.adapter = heightSpinnerAdapterUpdate
        lengthSpinUpdate.adapter = lengthSpinnerAdapterUpdate
        weightSpinUpdate.adapter = weightSpinnerAdapterUpdate

        ageSpinUpdate.onItemSelectedListener = this
        breedSpinUpdate.onItemSelectedListener = this
        heightSpinUpdate.onItemSelectedListener = this
        lengthSpinUpdate.onItemSelectedListener = this
        weightSpinUpdate.onItemSelectedListener = this
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
            R.id.spinnerAgeUpdate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataAge = ageSpinUpdate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataAge, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerBreedUpdate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataBreed = breedSpinUpdate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataBreed, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerHeightUpdate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpinUpdate.selectedItem.toString() + " x " + lengthSpinUpdate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerLengthUpdate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpinUpdate.selectedItem.toString() + " x " + lengthSpinUpdate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerWeightUpdate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataWeight = weightSpinUpdate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataWeight, Toast.LENGTH_LONG).show()
                }
            }
        }
        // you can also use this command anywhere outside of the onItemSelected
        //       textView.setText(spinner.selectedItem.toString())
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        Toast.makeText(this, "Nothing Selected", Toast.LENGTH_LONG).show()
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