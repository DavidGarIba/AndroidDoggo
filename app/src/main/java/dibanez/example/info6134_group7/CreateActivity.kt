package dibanez.example.info6134_group7

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class CreateActivity : AppCompatActivity(), OnItemSelectedListener {

    val CHANNEL_ID = "channelID"
    val CHANNEL_NAME = "channelName"
    val NOTIFICATION_ID = 0

    //variables for data elements
    lateinit var nameETCreate: EditText
    lateinit var genderRGCreate: RadioGroup
    lateinit var radioButtonMale: RadioButton
    lateinit var radioButtonFemale : RadioButton

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

    var currentName:String = ""
    var currentRadioValue: String = ""
    var currentAge: String = ""
    var currentBreed: String = ""
    var currentWeight: String = ""
    var currentHeight: String = ""
    var currentLength: String = ""
    var currentStreet: String = ""
    var currentZip:String = ""
    var currentCity:String = ""
    var currentState:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        createNotificationChannel()


    }

    override fun onResume() {
        super.onResume()

        //assign variables to corresponding views
        nameETCreate = findViewById(R.id.editTextNameCreate)
        genderRGCreate = findViewById(R.id.radioGroupGenderUpdate)

        radioButtonMale = findViewById(R.id.radioButtonMaleUpdate)
        radioButtonFemale = findViewById(R.id.radioButtonFemaleUpdate)

        streetETCreate = findViewById(R.id.editTextStreetCreate)
        zipETCreate = findViewById(R.id.editTextZipCreate)
        cityETCreate = findViewById(R.id.editTextCityCreate)
        stateETCreate = findViewById(R.id.editTextStateCreate)

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

        restoreData()
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            SecondActivity.receiveDogName = s.toString()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //actions for selected options in the spinner
        when(p0?.id) {
            R.id.spinnerAgeCreate-> {
                if (p2 != 0) {
                    SecondActivity.receiveDogAge = ageSpinCreate.selectedItem.toString()
//                    Toast.makeText(this, SecondActivity.receiveDogAge, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerBreedCreate-> {
                if (p2 != 0) {
                    SecondActivity.receiveDogBreed = breedSpinCreate.selectedItem.toString()
//                    Toast.makeText(this, SecondActivity.receiveDogBreed, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerHeightCreate-> {
                if (p2 != 0) {
                    SecondActivity.receiveDogHeight = heightSpinCreate.selectedItem.toString()
                    SecondActivity.receiveDogDimensions = "Height: ${heightSpinCreate.selectedItem.toString()},\nLength: ${lengthSpinCreate.selectedItem.toString()},\nWeight: ${weightSpinCreate.selectedItem.toString()}"

//                    Toast.makeText(this, SecondActivity.receiveDogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerLengthCreate-> {
                if (p2 != 0) {
                    SecondActivity.receiveDogLength = lengthSpinCreate.selectedItem.toString()
                    SecondActivity.receiveDogDimensions = "Height: ${heightSpinCreate.selectedItem.toString()},\nLength: ${lengthSpinCreate.selectedItem.toString()},\nWeight: ${weightSpinCreate.selectedItem.toString()}"//                    Toast.makeText(this, SecondActivity.receiveDogDataDimensions, Toast.LENGTH_LONG).show()
                }
            }
            R.id.spinnerWeightCreate-> {
                if (p2 != 0) {
                    SecondActivity.receiveDogWeight = weightSpinCreate.selectedItem.toString()
                    SecondActivity.receiveDogDimensions = "Height: ${heightSpinCreate.selectedItem.toString()},\nLength: ${lengthSpinCreate.selectedItem.toString()},\nWeight: ${weightSpinCreate.selectedItem.toString()}"//                    Toast.makeText(this, SecondActivity.receiveDogWeight, Toast.LENGTH_LONG).show()
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

    }

    fun addData(){
        var dogObject: DataType = DataType(
            SecondActivity.receiveDogName,
            SecondActivity.receiveDogAge,
            SecondActivity.receiveDogGender,
            SecondActivity.receiveDogBreed,

            SecondActivity.receiveDogDimensions,
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
                SecondActivity.receiveDogGender = getString(R.string.male)
                currentRadioValue = getString(R.string.male)
//                Toast.makeText(this, SecondActivity.receiveDogGender,Toast.LENGTH_SHORT).show()
            }
            R.id.radioButtonFemaleUpdate-> {
                SecondActivity.receiveDogGender = getString(R.string.female)
                currentRadioValue = getString(R.string.female)
//                Toast.makeText(this, SecondActivity.receiveDogGender,Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun onAddClicked(view: View) {
        if (SecondActivity.receiveDogHeight == "" ||SecondActivity.receiveDogLength == "" || SecondActivity.receiveDogWeight == "" || SecondActivity.receiveDogAge == "" || SecondActivity.receiveDogName == "" || SecondActivity.receiveDogGender == "" || SecondActivity.receiveDogBreed == "" || TextUtils.isEmpty(streetETCreate.text.toString()) || TextUtils.isEmpty(zipETCreate.text.toString()) || TextUtils.isEmpty(cityETCreate.text.toString()) || TextUtils.isEmpty(stateETCreate.text.toString())) {
            Toast.makeText(this, "Complete all details",Toast.LENGTH_SHORT).show()
        } else {
            onGeocode()
            addData()
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)

//            val intent = Intent(this, MainActivity::class.java)
//            val pendingIntent = TaskStackBuilder.create(this).run{
//                addNextIntentWithParentStack(intent)
//                getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//            }
//            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle(getString(R.string.doggo))
//                .setContentText(getString(R.string.createNotification))
//                .setSmallIcon(R.drawable.dogo_logo)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(pendingIntent)
//                .build()
//
//            val notificationManager = NotificationManagerCompat.from(this)
//            notificationManager.notify(NOTIFICATION_ID, notification)
//            finish()

           var flag = "true"
           val prefsEditor = getSharedPreferences("SharedPref", Context.MODE_PRIVATE).edit()
           prefsEditor.putString("flag", flag.toString())
           prefsEditor.apply()
        }
//        latLonTVCreate.text = SecondActivity.receiveDogName.toString() + " " + SecondActivity.receiveLat.toString() + " " + SecondActivity.receiveLon.toString() +  " " + SecondActivity.receiveDogWeight.toString() + " "  + SecondActivity.receiveDogAge.toString() + " " + SecondActivity.receiveDogGender.toString() + " " + SecondActivity.receiveDogDimensions.toString()

    }

    fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply{
                lightColor = R.color.orange_130
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    fun restoreData(){
        val data = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
        var checkFlag = data.getString("flag", "")
        if(checkFlag == "false")
        {
            var stringData = data.getString("sharedPrefCreate", "")
            var name = ((stringData?.substringAfter("name:"))?.substringBefore(","))
            var gender = ((stringData?.substringAfter("gender:"))?.substringBefore(","))
            var breed = ((stringData?.substringAfter("breed:"))?.substringBefore(","))
            var age = ((stringData?.substringAfter("age:"))?.substringBefore(","))
            var weight = ((stringData?.substringAfter("weight:"))?.substringBefore(","))
            weight = weight?.replace("\\s".toRegex(), "")
            var height = ((stringData?.substringAfter("height:"))?.substringBefore(","))
            height = height?.replace("\\s".toRegex(), "")
            var length = ((stringData?.substringAfter("length:"))?.substringBefore(","))
            length = length?.replace("\\s".toRegex(), "")
            var street = ((stringData?.substringAfter("street:"))?.substringBefore(","))
            var zip = ((stringData?.substringAfter("zip:"))?.substringBefore(","))
            var city = ((stringData?.substringAfter("city:"))?.substringBefore(","))
            var state = stringData?.substringAfter("state:")

            SecondActivity.receiveDogName = name.toString()
            SecondActivity.receiveDogAge = age.toString()
            SecondActivity.receiveDogGender = gender.toString()
            SecondActivity.receiveDogHeight = height.toString()
            SecondActivity.receiveDogLength = length.toString()
            SecondActivity.receiveDogWeight = weight.toString()
            

            nameETCreate.setText(name?:"")
            streetETCreate.setText(street?:"")
            zipETCreate.setText(zip?:"")
            cityETCreate.setText(city?:"")
            stateETCreate.setText(state?:"")

             when(breed) {
                        "Australian Shepard" -> breedSpinCreate.setSelection(1)
                        "Beagle" -> breedSpinCreate.setSelection(2)
                        "Corgi" -> breedSpinCreate.setSelection(3)
                        "French Bulldog" -> breedSpinCreate.setSelection(4)
                        "Golden Retriever" -> breedSpinCreate.setSelection(5)
                        "Pomerian" -> breedSpinCreate.setSelection(6)
                        "Shiba Inu" -> breedSpinCreate.setSelection(7)
                        "" -> breedSpinCreate.setSelection(0)
                    }
        if(age == null || age == "Age" ){
            ageSpinCreate.setSelection(0)
        }else{
            age
            ageSpinCreate.setSelection((age).toInt())
        }

        if(weight == null || weight == "Weight"){
            weightSpinCreate.setSelection(0)
        }else {
            weight = weight.substringBefore("kg")
            weightSpinCreate.setSelection((weight).toInt())
        }

        if(height == null || height == "Height"){
            heightSpinCreate.setSelection(0)
        }else{
            height = height.substringBefore("cm")
            heightSpinCreate.setSelection((height).toInt())
        }

        if(length == null || length == "Length"){
            lengthSpinCreate.setSelection(0)
        }else{
            length = length.substringBefore("cm")
            lengthSpinCreate.setSelection((length).toInt())
        }


            if(gender == "Male"){
                radioButtonMale.isChecked = true
            }else{
                radioButtonFemale.isChecked = true
            }

        }
    }

    override fun onPause() {
         currentName = nameETCreate.getText().toString()
         currentAge = ageSpinCreate.selectedItem.toString()
         currentBreed = breedSpinCreate.selectedItem.toString()
         currentWeight = weightSpinCreate.selectedItem.toString()
         currentHeight = heightSpinCreate.selectedItem.toString()
         currentLength = lengthSpinCreate.selectedItem.toString()
         currentStreet = streetETCreate.getText().toString()
         currentCity = cityETCreate.getText().toString()
         currentState = stateETCreate.getText().toString()
         currentZip = zipETCreate.getText().toString()

        var stringValue = "name:${currentName}, gender:${currentRadioValue}, breed:${currentBreed}, age:${currentAge}, weight: ${currentWeight}, height:${currentHeight}, length:${currentLength}, street:${currentStreet}, zip: ${currentZip}, city:${currentCity}, state:${currentState} "
        val prefsEditor = getSharedPreferences("SharedPref", Context.MODE_PRIVATE).edit()
        prefsEditor.putString("sharedPrefCreate", stringValue.toString())
        prefsEditor.apply()
        super.onPause()
    }


    override fun onStop() {
            val prefsEditor = getSharedPreferences("SharedPref", Context.MODE_PRIVATE).edit()
            prefsEditor.putString("flag", "false")
            prefsEditor.apply()
            super.onStop()

    }
}