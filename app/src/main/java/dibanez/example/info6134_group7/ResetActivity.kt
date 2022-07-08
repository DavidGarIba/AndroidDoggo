package dibanez.example.info6134_group7

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ResetActivity : AppCompatActivity() {

    lateinit var editTextReset: EditText

    private lateinit var auth: FirebaseAuth

    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        editTextReset = findViewById(R.id.editTextTextEmailReset)
        auth = Firebase.auth

    }

    override fun onResume() {
        super.onResume()
        editTextReset.addTextChangedListener(textWatcher)
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            email = s.toString()
        }
    }

    fun onResetPasswordClicked(view: View) {
        if (email.isNullOrBlank()) {
            Toast.makeText(baseContext, "Please enter valid email",
                Toast.LENGTH_LONG).show()
        } else {
            auth.sendPasswordResetEmail(email)
            Toast.makeText(baseContext, "Reset password link sent to ${email}",
                Toast.LENGTH_LONG).show()
            finish()
        }
    }
}