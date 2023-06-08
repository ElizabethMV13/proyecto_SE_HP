package com.example.ejercicio2

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.ejercicio2.PrimerFragment
import com.example.ejercicio2.R
import com.example.ejercicio2.SegundoFragment
import com.example.ejercicio2.TerceroFragment
import com.example.ejercicio2.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity(), AudioControlListener {

    private lateinit var mp: MediaPlayer

    private lateinit var binding: ActivityMainBinding
    lateinit var  navegation :BottomNavigationView



    private val opNavMenu = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.itemFragment1 -> {
                supportFragmentManager.commit {
                    replace<PrimerFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemFragment2 -> {
                supportFragmentManager.commit {
                    replace<SegundoFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")

                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.itemFragment3 -> {
                supportFragmentManager.commit {
                    replace<TerceroFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)



        setContentView(binding.root)

        mp = MediaPlayer.create(this, R.raw.music2)
        mp.isLooping = true // Reproducir el audio de forma cíclica
        mp.start()

        navegation = findViewById(R.id.navMenu)
        navegation.setOnNavigationItemSelectedListener(opNavMenu)

        supportFragmentManager.commit {
            replace<PrimerFragment>(R.id.frameContainer)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }



    }


    interface AudioControlListener {
        fun pauseAudio()
        fun resumeAudio()
    }

    override fun onStart() {
        super.onStart()
        mp.start()
    }

    override fun onResume() {
        super.onResume()
    }


    override fun onPause() {
        super.onPause()
        mp.pause()
    }

    override fun onStop() {
        super.onStop()
        mp.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mp.pause()
        mp.start()
        mp?.release()
    }

    override fun onRestart() {
        super.onRestart()
        mp.start()
    }

    override fun pauseAudio() {
        mp.pause()
    }

    override fun resumeAudio() {
        mp.start()
    }
}