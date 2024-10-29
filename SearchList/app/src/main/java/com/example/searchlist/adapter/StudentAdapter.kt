package com.example.searchlist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.searchlist.R
import com.example.searchlist.model.StudentData.Student

class StudentAdapter(private var students: List<Student>) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val mssvTextView: TextView = itemView.findViewById(R.id.mssvTextView)

        fun bind(student: Student) {
            nameTextView.text = student.name
            mssvTextView.text = student.mssv
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

    fun updateList(newStudents: List<Student>) {
        students = newStudents
        notifyDataSetChanged()
    }
}
