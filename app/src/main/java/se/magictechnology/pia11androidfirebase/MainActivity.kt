package se.magictechnology.pia11androidfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import layout.FruitAdapter

class MainActivity : AppCompatActivity() {

    var fadapter = FruitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val personRecview = findViewById<RecyclerView>(R.id.fruitRV)

        personRecview.adapter = fadapter
        personRecview.layoutManager = LinearLayoutManager(this)

        loadFruits()

        findViewById<Button>(R.id.addFruitButton).setOnClickListener {
            var addfruitname = findViewById<EditText>(R.id.addFruitET).text.toString()

            val database = Firebase.database
            val fruitSallad = database.getReference("fruitsallad")
            val somefruit = Fruit(addfruitname, "")
            fruitSallad.push().setValue(somefruit)

            loadFruits()
        }

        val database = Firebase.database

        //val myRef = database.getReference("testar")
        //myRef.setValue("Tjena världen")

        // Spara frukt
        /*
        val banan = Fruit("Banan", "Gul")
        val fruitRef = database.getReference("androidfruit")
        fruitRef.setValue(banan)
        */

        // Hämta data
        /*
        val myRef = database.getReference("androidfruit")

        myRef.get().addOnSuccessListener {
            val thefruit = it.getValue<Fruit>()

            Log.i("pia11debug", thefruit!!.fruitname!!)
        }
        */


        val fruitSallad = database.getReference("fruitsallad")

        // Spara med auto id
        /*
        val somefruit = Fruit("Kiwi", "Grön")
        fruitSallad.push().setValue(somefruit)
         */

        // Hämta lista med objekt
        /*
        fruitSallad.get().addOnSuccessListener {
            val allfruits = mutableListOf<Fruit>()
            it.children.forEach { childsnap ->
                allfruits.add(childsnap.getValue<Fruit>()!!)
            }

            for (fruit in allfruits) {
                Log.i("pia11debug", fruit.fruitname!!)
            }
        }
         */
    }

    fun loadFruits() {

        val database = Firebase.database

        val fruitSallad = database.getReference("fruitsallad")

        fruitSallad.get().addOnSuccessListener {
            val fbfruits = mutableListOf<Fruit>()
            it.children.forEach { childsnap ->
                fbfruits.add(childsnap.getValue<Fruit>()!!)
            }

            fadapter.allfruit = fbfruits
            fadapter.notifyDataSetChanged()

        }
    }



}

data class Fruit(val fruitname : String? = null, val fruitcolor : String? = null) {

}