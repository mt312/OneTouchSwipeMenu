# OneTouchSwipeMenu

Swipe menu for RecrclerView

![](https://raw.github.com/mt312/OneTouchSwipeMenu/master/readmess.png)

# build.gradle

gradle
```
repositories {
    maven { url 'https://raw.github.com/mt312/OneTouchSwipeMenu/master/repository' }
}
```

gradle in app
```
dependencies {
    implementation 'com.mt312:onetouchswipemenu:1.0'
}
```

# Swipeable layout

```
<androidx.constraintlayout.widget.ConstraintLayout
        android:id="@+id/foregroundKnobLayout" />
        
<LinearLayout
        android:id="@+id/backgroundLeftButtonLayout">
        <ImageButton android:id="@+id/infoButton" />
</LinearLayout>

<LinearLayout
        android:id="@+id/backgroundRightButtonLayout">
        <ImageButton android:id="@+id/shareButton" />
        <ImageButton android:id="@+id/searchButton" />
        <ImageButton android:id="@+id/deleteButton" />
</LinearLayout>
```

# RecyclerView

```kotlin
import com.mt312.library.OneTouchHelperCallback

class MainActivity: AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = RecyclerAdapter((0..29).toMutableList())
        OneTouchHelperCallback(recyclerView).build()
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
```

# License
```
MIT License

Copyright (c) 2021 MT312

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
