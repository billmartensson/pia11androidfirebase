package layout

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import se.magictechnology.pia11androidfirebase.Fruit
import se.magictechnology.pia11androidfirebase.R

class FruitAdapter : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    var allfruit = mutableListOf<Fruit>()

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val fruitName : TextView

        init {
            fruitName = view.findViewById(R.id.fruitNameTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        Log.i("pia11debug", "SKAPA RAD")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i("pia11debug", "RITA RAD " + position.toString())

        holder.fruitName.text = allfruit[position].fruitname
    }

    override fun getItemCount(): Int {
        return allfruit.size
    }

}