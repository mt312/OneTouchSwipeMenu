package com.mt312.onetouchswipemenu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mt312.library.OneTouchHelperCallback
import java.util.*

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = RecyclerAdapter((0..29).toMutableList())
        OneTouchHelperCallback(recyclerView).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    private inner class RecyclerAdapter(private val items: MutableList<Int>): RecyclerView.Adapter<RecyclerViewHolder>(), OneTouchHelperCallback.DragAdapter {

        override fun onItemMove(fromPosition: Int, toPosition: Int) {
            Collections.swap(items, fromPosition, toPosition)
            notifyDataSetChanged()
            Toast.makeText(applicationContext, "$fromPosition -> $toPosition moved!", Toast.LENGTH_SHORT).show()
        }

        override fun getItemCount(): Int = items.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent,false)
            return RecyclerViewHolder(view).also { viewHolder ->
                viewHolder.foregroundKnobLayout.setOnClickListener {
                    val position = viewHolder.adapterPosition
                    Toast.makeText(applicationContext, "$position knob clicked!", Toast.LENGTH_SHORT).show()
                }

                viewHolder.infoButton.setOnClickListener {
                    val position = viewHolder.adapterPosition
                    Toast.makeText(applicationContext, "$position info button clicked!", Toast.LENGTH_SHORT).show()
                }

                viewHolder.shareButton.setOnClickListener {
                    val position = viewHolder.adapterPosition
                    Toast.makeText(applicationContext, "$position share button clicked!", Toast.LENGTH_SHORT).show()
                }

                viewHolder.searchButton.setOnClickListener {
                    val position = viewHolder.adapterPosition
                    Toast.makeText(applicationContext, "$position search button clicked!", Toast.LENGTH_SHORT).show()
                }

                viewHolder.deleteButton.setOnClickListener {
                    val position = viewHolder.adapterPosition
                    items.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }

        override fun onBindViewHolder(viewHolder: RecyclerViewHolder, position: Int) {
            viewHolder.bind(items[position], position)
        }
    }

    private class RecyclerViewHolder(view: View): RecyclerView.ViewHolder(view), OneTouchHelperCallback.SwipeViewHolder {

        override val foregroundKnobLayout: ViewGroup = view.findViewById(R.id.foregroundKnobLayout)
        override val backgroundLeftButtonLayout: ViewGroup = view.findViewById(R.id.backgroundLeftButtonLayout)
        override val backgroundRightButtonLayout: ViewGroup = view.findViewById(R.id.backgroundRightButtonLayout)
        override val canRemoveOnSwipingFromRight: Boolean get() = true

        val infoButton: ImageButton = view.findViewById(R.id.infoButton)
        val shareButton: ImageButton = view.findViewById(R.id.shareButton)
        val searchButton: ImageButton = view.findViewById(R.id.searchButton)
        val deleteButton: ImageButton = view.findViewById(R.id.deleteButton)

        val textView: TextView = view.findViewById(R.id.textView)

        fun bind(item: Int, position: Int) {
            textView.text = position.toString() + ". " + item.toString()
        }
    }
}
