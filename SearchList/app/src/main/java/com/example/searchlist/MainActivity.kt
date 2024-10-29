package com.example.searchlist

import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchlist.adapter.StudentAdapter
import com.example.searchlist.model.StudentData.Student

class MainActivity : AppCompatActivity() {

    private lateinit var studentAdapter: StudentAdapter
    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    val students = listOf(
        Student("Nguyen Van A", "20213456"),
        Student("Le Thi B", "20213457"),
        Student("Tran Van C", "20213458"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        studentAdapter = StudentAdapter(students)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.length > 2) {
                    val filteredList = students.filter {
                        it.name.contains(newText, ignoreCase = true) ||
                                it.mssv.contains(newText, ignoreCase = true)
                    }
                    studentAdapter.updateList(filteredList)
                } else {
                    studentAdapter.updateList(students)
                }
                return true
            }
        })
    }
}