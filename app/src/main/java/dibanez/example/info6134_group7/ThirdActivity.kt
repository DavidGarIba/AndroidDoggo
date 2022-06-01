package dibanez.example.info6134_group7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    fun backBtn(view: View) {
        val intent = Intent(this,SecondActivity::class.java)
        startActivity(intent)
    }
}