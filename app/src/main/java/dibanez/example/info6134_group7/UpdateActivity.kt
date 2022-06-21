package dibanez.example.info6134_group7

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class UpdateActivity : AppCompatActivity(),OnItemSelectedListener  {

    //variables for data elements
    lateinit var nameETUpdate: TextView
    lateinit var genderRGUpdate: RadioGroup

    lateinit var ageSpinUpdate: Spinner
    lateinit var breedSpinUpdate: Spinner
    lateinit var heightSpinUpdate: Spinner
    lateinit var lengthSpinUpdate: Spinner
    lateinit var weightSpinUpdate: Spinner
    lateinit var radioButtonMale: RadioButton
    lateinit var radioButtonFemale: RadioButton
    lateinit var streetETCreate: EditText
    lateinit var zipETCreate: EditText
    lateinit var cityETCreate: EditText
    lateinit var stateETCreate: EditText
    lateinit var latLonTVCreate: TextView
    companion object{
        var DogName: String = ""
        var DogAge: String = ""
        var DogGender: String = ""
        var DogDataDimensions: String = ""
        var DogLat: Double = 0.0
        var DogLon: Double = 0.0
    }


    var currentAge: String = ""
    var currentGender: String = ""
    var currentHeight: String = ""
    var currentWeight: String = ""
    var currentLength: String = ""
    var currentBreed: String = ""
    var currentLat: Double = 0.0
    var currentLon: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)


        //assign variables to corresponding views
        nameETUpdate = findViewById(R.id.editTextNameUpdate)
        genderRGUpdate = findViewById(R.id.radioGroupGenderUpdate)
        ageSpinUpdate = findViewById(R.id.spinnerAgeUpdate)
        breedSpinUpdate = findViewById(R.id.spinnerBreedUpdate)
        heightSpinUpdate = findViewById(R.id.spinnerHeightUpdate)
        lengthSpinUpdate = findViewById(R.id.spinnerLengthUpdate)
        weightSpinUpdate = findViewById(R.id.spinnerWeightUpdate)
        radioButtonMale = findViewById(R.id.radioButtonMaleUpdate)
        radioButtonFemale = findViewById(R.id.radioButtonFemaleUpdate)

        streetETCreate = findViewById(R.id.editTextStreetCreate)
        zipETCreate = findViewById(R.id.editTextZipCreate)
        cityETCreate = findViewById(R.id.editTextCityCreate)
        stateETCreate = findViewById(R.id.editTextStateCreate)
        latLonTVCreate = findViewById(R.id.textViewLatLonCreate)


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

    override fun onResume() {
        super.onResume()

        nameETUpdate.text = DogName
        ageSpinUpdate.setSelection(DogAge.toInt())

        if(DogGender == "Male"){
            radioButtonMale.isChecked = true
        }else{
            radioButtonFemale.isChecked = true
        }
        var Height: String = ((DogDataDimensions!!.substringAfter("Height:")).substringBefore("cm"))
        var Length: String = ((DogDataDimensions!!.substringAfter("Length:")).substringBefore("cm"))
        var Weight: String = ((DogDataDimensions!!.substringAfter("Weight:")).substringBefore("kg"))
        var Breed: String = ((DogAge!!.substringBefore(",")))

        heightSpinUpdate.setSelection(Height.toInt())
        lengthSpinUpdate.setSelection(Length.toInt())
        weightSpinUpdate.setSelection(Weight.toInt())
        //breedSpinUpdate.setSelection(Breed.toInt())

        ConvertLatLonToAddress()
    }

    fun ConvertAddressToLatLon(){
        val addressCompiler = streetETCreate.text.toString() + ", " + cityETCreate.text.toString() + ", " + stateETCreate.text.toString() + ", " + zipETCreate.text.toString() + ", "
        val geocode = Geocoder(this, Locale.getDefault())
        val addList = geocode.getFromLocationName(addressCompiler, 1)
        currentLat = addList.get(0).latitude
        currentLon = addList.get(0).longitude
    }
    fun ConvertLatLonToAddress(){}

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //actions for selected options in the spinner
        when(p0?.id) {
            R.id.spinnerAgeUpdate-> {
                if (p2 != 0) {
                    currentAge = ageSpinUpdate.selectedItem.toString()
                }else{
                    currentAge = DogAge
                }
            }
            R.id.spinnerBreedUpdate-> {
                if (p2 != 0) {
                    currentBreed = breedSpinUpdate.selectedItem.toString()
                }
                else{
                    currentBreed = DogDataDimensions
                }
            }
            R.id.spinnerHeightUpdate-> {
                if (p2 != 0) {
                    currentHeight = heightSpinUpdate.selectedItem.toString()
                }
                else{
                    currentHeight = ((DogDataDimensions!!.substringAfter("Height:")).substringBefore(","))
                }
            }
            R.id.spinnerLengthUpdate-> {
                if (p2 != 0) {
                    currentLength = lengthSpinUpdate.selectedItem.toString()
                }
                else{
                    currentLength = ((DogDataDimensions!!.substringAfter("Length:")).substringBefore(","))
                }
            }
            R.id.spinnerWeightUpdate-> {
                if (p2 != 0) {
                    currentWeight = weightSpinUpdate.selectedItem.toString()
                }else{
                    currentWeight = ((DogDataDimensions!!.substringAfter("Weight:")))
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
                currentGender = getString(R.string.male)
            }
            R.id.radioButtonFemaleUpdate-> {
                currentGender = getString(R.string.female)
            }
        }
    }

    fun btnSave(view: View) {
            ConvertAddressToLatLon()

            SecondActivity.receiveDogName = DogName
            SecondActivity.receiveDogAge = currentAge
            SecondActivity.receiveDogGender = "${currentBreed}, \n${currentGender}"
            SecondActivity.receiveDogDataDimensions = "Height:${currentHeight},\nLength:${currentLength},\nWeight:${currentWeight}"
            SecondActivity.receiveLat = currentLat
            SecondActivity.receiveLon = currentLon

            SecondActivity().updateData()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)

    }
}