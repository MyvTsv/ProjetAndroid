package com.example.suballigator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.suballigator.entity.Initiator
import com.example.suballigator.entity.Participant
import kotlinx.coroutines.runBlocking

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            runBlocking { insertDataAPI(application) }

            val initiators: List<Initiator>? = getInitiator(application)

            val existe = initiatorExist(initiators!!, email, password)

            }
        }

        private fun initiatorExist(initiators : List<Initiator>, email:String, password:String):Boolean{
            initiators?.forEach { initiator ->
                if (email == initiator.email && password == initiator.password) {
                    return true
                }
            }
            return false
        }

}