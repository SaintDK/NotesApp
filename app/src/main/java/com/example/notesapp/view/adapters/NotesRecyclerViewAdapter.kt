package com.example.notesapp.view.adapters

import android.animation.ValueAnimator
import android.content.Intent
import android.transition.*
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity

import com.example.notesapp.model.placeholder.PlaceholderContent.PlaceholderItem
import com.example.notesapp.databinding.FragmentStartItemBinding
import com.example.notesapp.model.entities.EntityNotes
import com.example.notesapp.view.activities.AddActivity
import com.example.notesapp.view.activities.UpdateActivity


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class NotesRecyclerViewAdapter(
    private val values: List<EntityNotes>
) : RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentStartItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.tag
        holder.contentView.text = item.description
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentStartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content

        init {
            binding.root.setOnClickListener {
                val item = values[adapterPosition]
//                Toast.makeText(itemView.context, "Clicked item: ${item.name}", Toast.LENGTH_SHORT).show()

                // Создать объект ValueAnimator для анимации альфа-канала
                val alphaAnimator = ValueAnimator.ofFloat(1f, 0.5f, 1f)
                alphaAnimator.duration = 300
                // Задать слушатель обновления значения альфа-канала
                alphaAnimator.addUpdateListener { animator ->
                    val alpha = animator.animatedValue as Float
                    binding.root.alpha = alpha
                }
                // Запустить анимацию альфа-канала
                alphaAnimator.start()

                val context = itemView.context
                val intent = Intent(context, UpdateActivity::class.java)
                intent.putExtra("item_id", item.id.toString())
//                Log.d("tag123123", "${item.id}" + " itemId")
//                intent.putExtra("item_tag", item.tag)
//                intent.putExtra("item_description", item.description)
                context.startActivity(intent)
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}