package com.example.ejercicio2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicio2.model.student.Student
import com.example.ejercicio2.model.student.StudentAdapter
import com.example.ejercicio2.model.student.StudentApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SegundoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SegundoFragment : Fragment(), StudentAdapter.ClickListener {

    private val studentList: MutableList<Student> = mutableListOf()
    lateinit var adapterStudent: StudentAdapter
    lateinit var recyclerView: RecyclerView

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onClick(position: String){
        //Log.d("MainActivity", "----------------------Valor de id: ${position}--------------------")
        startActivity(Intent(requireContext(), Details::class.java).putExtra("fragment", 2).putExtra("id", position))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_segundo, container, false)


        // Configurar el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewEstudent)


        fetchStudentData(view)

        return view
    }

    private  fun showData(users: List<Student>){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = StudentAdapter(requireContext(),studentList,this@SegundoFragment)

        }

    }

    private fun fetchStudentData(view: View) {

        // Crear una instancia de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hp-api.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(StudentApiService::class.java)

        val call = api.getStudents()

        // Hacer la llamada a la API para obtener los datos de los estudiantes
        var retroData = call.enqueue(object : Callback<ArrayList<Student>> {
            override fun onResponse(
                call: Call<ArrayList<Student>>,
                response: Response<ArrayList<Student>>
            ) {

                view.findViewById<ProgressBar>(R.id.progressStudent).visibility = View.GONE
                //Log.d("exitoso","onResponse {${response.body()!![0].actor}}")

                showData(response.body()!!)
                if (response.isSuccessful) {
                    val students = response.body()
                    students?.let {
                        // Agregar los estudiantes a la lista
                        studentList.addAll(students)
                        adapterStudent = StudentAdapter(requireContext(), studentList,this@SegundoFragment )
                        recyclerView.adapter = adapterStudent
                        //Log.d("data", students.toString())
                    }
                } else {
                    val builder = AlertDialog.Builder(requireContext())
                    builder.setTitle(R.string.titleError2)
                        .setMessage(R.string.error2)
                        .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                            dialog.dismiss()
                        }

                    val dialog = builder.create()
                    dialog.show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                val builder = AlertDialog.Builder(requireContext())
                builder.setTitle(R.string.titleError1)
                    .setMessage(R.string.error1)
                    .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                        dialog.dismiss()
                    }

                val dialog = builder.create()
                dialog.show()

                Log.d("falla","onFailures")
            }
        })

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment secondFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SegundoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}