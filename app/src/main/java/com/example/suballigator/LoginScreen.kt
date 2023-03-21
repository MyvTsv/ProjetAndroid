package com.example.suballigator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.suballigator.entity.Initiator
import com.example.suballigator.entity.Participant
import kotlinx.coroutines.runBlocking
import org.w3c.dom.Text
import kotlin.math.log

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val emailInput = findViewById<EditText>(R.id.emailInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)

        passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()

        loginButton.setOnClickListener {

            loginButton.isEnabled = false
            it.isEnabled = false

            if(emailInput.text.toString().isEmpty() || passwordInput.text.toString().isEmpty()) {

                val email = emailInput.text.toString()
                val password = passwordInput.text.toString()

                runBlocking { insertDataAPI(application) }

                val initiators: List<Initiator>? = getInitiator(application)

                val existe = initiatorExist(initiators!!, email, password)

                if (existe) {
                    val nextPage = Intent(this, ListeDesFormationsAcitivty::class.java)
                    startActivity(nextPage)
                    finish()
                } else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Erreur")
                    builder.setMessage("Email ou mot de passe incorrect")
                    builder.setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            } else{
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Erreur")
                builder.setMessage("Veuillez remplir tous les champs")
                builder.setPositiveButton("OK") { dialog, which ->
                    dialog.dismiss()
                }
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
            loginButton.isEnabled = true
            it.isEnabled = true
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