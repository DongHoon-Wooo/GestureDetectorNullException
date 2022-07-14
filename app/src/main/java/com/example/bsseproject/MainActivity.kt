package com.example.bsseproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val recyclerView by lazy {
        findViewById<RecyclerView>(R.id.recyclerView)
    }

    private val gestureDetector: GestureDetector = GestureDetector(
        baseContext,
        object : GestureDetector.SimpleOnGestureListener() {
            var count = 0
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                count++
                return true
            }

            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                count++
                return true
            }

            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                count++
                return true
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.itemAnimator = null
        recyclerView.adapter = GestureAdapter((0..10).toList())

        recyclerView.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
        }
    }

    class GestureAdapter(val items: List<Int>) :
        RecyclerView.Adapter<GestureAdapter.GestureViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GestureViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false)
            return GestureViewHolder(view)
        }

        override fun onBindViewHolder(holder: GestureViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int {
            return items.count()
        }

        class GestureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(i: Int) {
                itemView.findViewById<TextView>(R.id.textView).text = "Item: $i"
                itemView.findViewById<TextView>(R.id.textView).setOnClickListener {
                    Toast.makeText(
                        it.context,
                        "OnClick",
                        Toast.LENGTH_SHORT
                    )
                }
            }
        }
    }
}