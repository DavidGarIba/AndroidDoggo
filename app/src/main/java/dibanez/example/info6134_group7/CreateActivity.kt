package dibanez.example.info6134_group7

import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class CreateActivity : AppCompatActivity(), OnItemSelectedListener {

    //variables for data elements
    lateinit var nameETCreate: EditText
    lateinit var genderRGCreate: RadioGroup


    lateinit var streetETCreate: EditText
    lateinit var zipETCreate: EditText
    lateinit var cityETCreate: EditText
    lateinit var stateETCreate: EditText
    lateinit var latLonTVCreate: TextView

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
        nameETCreate = findViewById(R.id.editTextNameCreate)
        genderRGCreate = findViewById(R.id.radioGroupGenderUpdate)

        streetETCreate = findViewById(R.id.editTextStreetCreate)
        zipETCreate = findViewById(R.id.editTextZipCreate)
        cityETCreate = findViewById(R.id.editTextCityCreate)
        stateETCreate = findViewById(R.id.editTextStateCreate)
        latLonTVCreate = findViewById(R.id.textViewLatLonCreate)

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

    fun onGeocode () {
        val addressCompiler = streetETCreate.text.toString() + ", " + cityETCreate.text.toString() + ", " + stateETCreate.text.toString() + ", " + zipETCreate.text.toString() + ", "
        val geocode = Geocoder(this, Locale.getDefault())
        val addList = geocode.getFromLocationName(addressCompiler, 1)
        SecondActivity.receiveLat = addList.get(0).latitude
        SecondActivity.receiveLon = addList.get(0).longitude
        latLonTVCreate.text = SecondActivity.receiveLat.toString() + " " + SecondActivity.receiveLon.toString()
    }

    fun addData(){
        var dogObject: DataType = DataType(
            SecondActivity.receiveDogName,
            SecondActivity.receiveDogGender,
            SecondActivity.receiveDogAge,
            SecondActivity.receiveDogDataDimensions,
            SecondActivity.receiveLat,
            SecondActivity.receiveLon)
        Firebase.database.reference.child("User/${MainActivity.userID}/${SecondActivity.receiveDogName}").setValue(dogObject)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "Data added successfully.",
                    Toast.LENGTH_SHORT).show()

            }
            .addOnFailureListener {
                Toast.makeText(baseContext, "Data added failed.",
                    Toast.LENGTH_SHORT).show()
            }
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

    fun onAddClicked(view: View) {

        onGeocode()
        addData()
        finish()
    }
}