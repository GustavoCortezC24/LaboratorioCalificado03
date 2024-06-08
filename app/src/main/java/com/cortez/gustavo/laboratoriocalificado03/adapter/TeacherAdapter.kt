package com.cortez.gustavo.laboratoriocalificado03.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cortez.gustavo.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.cortez.gustavo.laboratoriocalificado03.model.Teacher
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cortez.gustavo.laboratoriocalificado03.R


class TeacherAdapter(private val context: Context, private val teacherList: List<Teacher>) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teacherList[position]
        holder.binding.tvNombre.text = teacher.name
        holder.binding.tvApellido.text = teacher.last_name
        Glide.with(context)
            .load(teacher.image_url)
            .apply(RequestOptions().placeholder(R.drawable.placeholder))
            .into(holder.binding.ivFoto)


        holder.itemView.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${teacher.phone_number}")
            }
            context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:${teacher.email}")
            }
            context.startActivity(intent)
            true
        }
    }

    override fun getItemCount() = teacherList.size
}