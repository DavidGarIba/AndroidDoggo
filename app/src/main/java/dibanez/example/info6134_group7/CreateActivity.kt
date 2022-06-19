package dibanez.example.info6134_group7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener

class CreateActivity : AppCompatActivity(), OnItemSelectedListener {

    //variables for data elements
    lateinit var nameETCreate: EditText
    lateinit var genderRGCreate: RadioGroup

    lateinit var ageSpinCreate: Spinner
    lateinit var breedSpinCreate: Spinner
    lateinit var heightSpinCreate: Spinner
    lateinit var lengthSpinCreate: Spinner
    lateinit var weightSpinCreate: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
    }

    override fun onResume() {
        super.onResume()

        //assign variables to corresponding views
        nameETCreate = findViewById(R.id.editTextPostNameCreate)
        genderRGCreate = findViewById(R.id.radioGroupGenderUpdate)
        ageSpinCreate = findViewById(R.id.spinnerAgeCreate)
        breedSpinCreate = findViewById(R.id.spinnerBreedCreate)
        heightSpinCreate = findViewById(R.id.spinnerHeightCreate)
        lengthSpinCreate = findViewById(R.id.spinnerLengthCreate)
        weightSpinCreate = findViewById(R.id.spinnerWeightCreate)

        nameETCreate.addTextChangedListener(textWatcher)

        //setting up the spinner adapters
        val ageSpinnerAdapterCreate = ArrayAdapter.createFromResource(
            this,
            R.array.ageArray,
            android.R.layout.simple_spinner_item
        )
        val breedSpinnerAdapterCreate = ArrayAdapter.createFromResource(
            this,
            R.array.breedArray,
            android.R.layout.simple_spinner_item
        )
        val heightSpinnerAdapterCreate = ArrayAdapter.createFromResource(
            this,
            R.array.heightArray,
            android.R.layout.simple_spinner_item
        )
        val lengthSpinnerAdapterCreate = ArrayAdapter.createFromResource(
            this,
            R.array.lengthArray,
            android.R.layout.simple_spinner_item
        )
        val weightSpinnerAdapterCreate = ArrayAdapter.createFromResource(
            this,
            R.array.weightArray,
            android.R.layout.simple_spinner_item
        )

        ageSpinnerAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        breedSpinnerAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        heightSpinnerAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        lengthSpinnerAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        weightSpinnerAdapterCreate.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        ageSpinCreate.adapter = ageSpinnerAdapterCreate
        breedSpinCreate.adapter = breedSpinnerAdapterCreate
        heightSpinCreate.adapter = heightSpinnerAdapterCreate
        lengthSpinCreate.adapter = lengthSpinnerAdapterCreate
        weightSpinCreate.adapter = weightSpinnerAdapterCreate

        ageSpinCreate.onItemSelectedListener = this
        breedSpinCreate.onItemSelectedListener = this
        heightSpinCreate.onItemSelectedListener = this
        lengthSpinCreate.onItemSelectedListener = this
        weightSpinCreate.onItemSelectedListener = this
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
            R.id.spinnerAgeCreate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataAge = ageSpinCreate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataAge, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerBreedCreate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataBreed = breedSpinCreate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataBreed, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerHeightCreate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpinCreate.selectedItem.toString() + " x " + lengthSpinCreate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerLengthCreate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataDimensions = heightSpinCreate.selectedItem.toString() + " x " + lengthSpinCreate.selectedItem.toString()
                    Toast.makeText(this, SecondActivity.dogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerWeightCreate-> {
                if (p2 != 0) {
                    SecondActivity.dogDataWeight = weightSpinCreate.selectedItem.toString()
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