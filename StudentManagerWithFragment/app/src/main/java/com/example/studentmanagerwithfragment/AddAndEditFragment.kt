package com.example.studentmanagerwithfragment

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Use the [AddAndEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddAndEditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
            param3 = it.getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", param3.toString())
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_and_edit, container, false)

        if(param1 != null && param2 != null && param3 > -1){
            view.findViewById<TextView>(R.id.textView_title).text = "CHỈNH SỬA THÔNG TIN SINH VIÊN"
            view.findViewById<EditText>(R.id.edit_hoten).setText(param1)
            view.findViewById<EditText>(R.id.edit_mssv).setText(param2)
        }

        val button_cancle = view.findViewById<Button>(R.id.button_cancle)
        val button_submit = view.findViewById<Button>(R.id.button_submit)

        button_cancle.setOnClickListener {
            findNavController().navigate(R.id.action_addAndEditFragment_to_mainFragment)
        }

        button_submit.setOnClickListener {
            AlertDialog.Builder(requireActivity())
                .setIcon(R.drawable.baseline_question_mark_24)
                .setTitle("Xác nhận lưu thông tin")
                .setMessage("Bạn chắc chắn muốn lưu thông tin?")
                .setPositiveButton("Ok") { _, _ ->
                    val newName = view.findViewById<EditText>(R.id.edit_hoten).text.toString().trim()
                    val newId = view.findViewById<EditText>(R.id.edit_mssv).text.toString().trim()
                    if(newName.isEmpty() || newId.isEmpty()){
                        Toast.makeText(requireActivity(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_LONG).show()
                    } else {
                        val arguments = Bundle()
                        arguments.putString("param1", newName)
                        arguments.putString("param2", newId)
                        arguments.putInt("param3", param3)
                        findNavController().navigate(R.id.action_addAndEditFragment_to_mainFragment, arguments)
                    }
                }
                .setNegativeButton("Cancle", null)
                .setCancelable(false)
                .show()
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddAndEditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: Int) =
            AddAndEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putInt(ARG_PARAM3, param3)
                }
            }
    }
}