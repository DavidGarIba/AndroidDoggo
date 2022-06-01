package dibanez.example.info6134_group7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

interface CellClickListener{
    fun onCellClickListener(lat: Double,lon: Double)
}


class SecondActivity : AppCompatActivity(),CellClickListener {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        val dogData: MutableList<DataType> = mutableListOf<DataType>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        dogData.add(DataType("Dog1","3","female","10 cm",41.5245,-70.6709))
        dogData.add(DataType("Dog2","3","female","10 cm",30.5245,-60.6709))
        dogData.add(DataType("Dog3","3","female","10 cm",20.5245,-50.5245))
        println("test1" + dogData)

    }

    override fun onResume() {
        super.onResume()
        viewManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(dogData,this)  // pass in data to be displayed
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView).apply{
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = recyclerAdapter  }
    }
    fun RecyclerViewOnView(){
        recyclerView = findViewById<RecyclerView>(R.id.RecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(dogData, this)
        recyclerAdapter.notifyDataSetChanged()
    }

    fun onClickSignout(view: View) {
        Firebase.auth.signOut()
        Toast.makeText(baseContext, "You signed out successfully.",
            Toast.LENGTH_SHORT).show()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun deleteBtn(view: View) {
        dogData.removeLast()
        recyclerView.adapter = RecyclerAdapter(dogData, this)
        recyclerAdapter.notifyDataSetChanged()
        println(dogData)
    }

    fun editBtn(view: View) {
        val intent = Intent(this,ThirdActivity::class.java)
        startActivity(intent)
    }

    fun addBtn(view: View) {
        val intent = Intent(this,ThirdActivity::class.java)
        startActivity(intent)
    }


    fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit() }

    override fun onCellClickListener( lat: Double, lon: Double) {
        val fragment = MapsFragment()
        supportFragmentManager.inTransaction { replace(R.id.fragmentContainerView, fragment) }
        MapsFragment.latitudeFrag = lat
        MapsFragment.longitudeFrag = lon
    }

    fun backBtn(view: View) {
        val fragment = RecyclerViewFragment()
        supportFragmentManager.inTransaction { replace(R.id.fragmentContainerView, fragment) }
        //println("test2" + dogData)

    }


}