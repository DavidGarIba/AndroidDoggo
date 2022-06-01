package dibanez.example.info6134_group7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val dataSet: MutableList<DataType>, private val cellClickListener: CellClickListener ) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewAge: TextView
        val textViewGender: TextView
        val textViewDimension: TextView


        init {
            // Define click listener for the ViewHolder's View.
            textViewName = view.findViewById(R.id.textViewName)
            textViewAge = view.findViewById(R.id.textViewAge)
            textViewGender = view.findViewById(R.id.textViewGender)
            textViewDimension = view.findViewById(R.id.textViewDimension)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycleview_item_layout, viewGroup, false)

        // add the 3 lines of code below to show 5 recycler items in the activity at a time
        val lp = view.getLayoutParams()
        lp.height = viewGroup.measuredHeight/5
        view.setLayoutParams(lp)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewName.text = dataSet[position].name.toString()
        viewHolder.textViewAge.text = dataSet[position].age.toString()
        viewHolder.textViewGender.text = dataSet[position].gender.toString()
        viewHolder.textViewDimension.text = dataSet[position].dimension.toString()
        viewHolder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(dataSet[position].lat, dataSet[position].lon)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
