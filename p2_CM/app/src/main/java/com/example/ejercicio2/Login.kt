package com.example.ejercicio2


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.ejercicio2.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import okhttp3.internal.Util.concat
import java.util.TimerTask

class Login : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding
    private var email = ""
    private var pass = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.buttonIngresar.setOnClickListener{
            if(!validaCampos()) return@setOnClickListener
            autenticaUsuario(email,pass)

            // binding.progressBar.visibility= View.VISIBLE
        }

        binding.buttonRegistro.setOnClickListener{
            //firebaseAuth.createUserWithEmailAndPassword()
            startActivity(Intent(this,Registrate::class.java))
        }

    }

    private fun manejaErrores(task: Task<AuthResult>){
        var errorCode =""
        var errorList = ""
        try {
            errorCode = (task.exception as FirebaseAuthException).errorCode
        }catch (e: Exception){
            errorCode= "NO_NETWORK"
        }

        when(errorCode){
            "ERROR_INVALID_EMAIL" ->{
                errorList = errorList + getString(R.string.errorl1) + " \n "
            }
            "ERROR_WRONG_PASSWORD" ->{
                errorList = errorList + getString(R.string.errorl2) + " \n "
            }
            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" ->{
                errorList = errorList + getString(R.string.errorl3) + " \n "
            }
            "ERROR_EMAIL_ALREADY_IN_USE" ->{
                errorList = errorList + getString(R.string.errorl4) + " \n "
            }
            "NO_USE_TOKEN_EXPIRED" ->{
                errorList = errorList + getString(R.string.errorl5) + " \n "
            }
            "NO_USER_NOT_FOUND" ->{
                errorList = errorList + getString(R.string.errorl6) + " \n "
            }
            "NO_WEAK_PASSWORD" ->{
                errorList = errorList + getString(R.string.errorl71)+ " \n "
            }
            "NO_NETWORK" ->{
                errorList = errorList + getString(R.string.errorl8) + " \n "
            }
            else-> {
                errorList = errorList + getString(R.string.errorl9) + " \n "
            }


        }

    }


    private fun autenticaUsuario(usr: String, pass: String){
        firebaseAuth.signInWithEmailAndPassword(usr,pass).addOnCompleteListener{ authResult ->
            if (authResult.isSuccessful){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }else{
                //binding.progressBar.visibility = View.GONE

                var errores = manejaErrores(authResult)

                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.errorl)
                    .setMessage(errores.toString())
                    .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                        dialog.dismiss()
                    }

                val dialog = builder.create()
                dialog.show()
            }
        }
    }

    /*            <ProgressBar
                android:id="@+id/progressBar"
                style="?android:attr/progressBarStyle"
                android:layout_width="90dp"
                android:layout_height="90dp"
                app:layout_constraintBottom_toTopOf="@+id/guideline20"
                app:layout_constraintEnd_toStartOf="@+id/guideline19"
                app:layout_constraintStart_toStartOf="@+id/guideline17"
                app:layout_constraintTop_toTopOf="@+id/guideline3" />
*/


    private fun validaCampos():Boolean{
        email = binding.emailInputEditText.text.toString().trim()
        pass = binding.passInputEditText.text.toString().trim()

        Log.d("exitoso","${email.isNotEmpty()}-----------${email.length}------------------onResponse {${pass}}---------${pass.isNotEmpty()}--- ${pass.length}")

        if(email.isNotEmpty()){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.errorl))
                .setMessage(getString(R.string.errorE))
                .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                    dialog.dismiss()
                }

            val dialog = builder.create()
            dialog.show()

            return false
        }

        if(pass.length < 6 || pass.isNotEmpty() ){
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.errorl))
                .setMessage(getString(R.string.errorC))
                .setPositiveButton(R.string.leyenda1) { dialog, _ ->
                    dialog.dismiss()
                }
            val dialog = builder.create()

            dialog.show()

            return false
        }

        return true
    }


}