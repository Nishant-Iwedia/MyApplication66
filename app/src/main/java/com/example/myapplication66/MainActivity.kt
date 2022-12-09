package com.example.myapplication66

import android.media.MediaRecorder.AudioSource
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(AudioFragment())

    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if(event!!.action == KeyEvent.ACTION_DOWN){
            when(event.keyCode){
                KeyEvent.KEYCODE_DPAD_LEFT ->{
                    loadFragment(BlankFragment())
                    return true
                }
                KeyEvent.KEYCODE_DPAD_RIGHT ->{
                    loadFragment(ChoosePvrTypeFragment())
                    return true
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }

    private fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.main_container, fragment)
            .commit()
    }
}

