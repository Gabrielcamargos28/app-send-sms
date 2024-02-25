package com.sendsms

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var editTelefone: EditText
    lateinit var editMensagem: EditText
    lateinit var btnEnviar: Button


    //@RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTelefone = findViewById(R.id.idEdtTelefone)
        editMensagem = findViewById(R.id.idEdtMensagem)
        btnEnviar = findViewById(R.id.idBtnEnviarMensagem)

        btnEnviar.setOnClickListener{
            val telefone = editTelefone.text.toString()
            val mensagem = editMensagem.text.toString()

            enviarMensagem(telefone,mensagem,this)
        }
    }

    fun enviarMensagem(telefone: String, mensagem: String, context: Context){
        try {
            val permissao = Manifest.permission.SEND_SMS

            if(ContextCompat.checkSelfPermission(context, permissao) == PackageManager.PERMISSION_GRANTED){
                val smsManager: SmsManager = SmsManager.getDefault()

                smsManager.sendTextMessage(telefone,null,mensagem,null,null)
                Toast.makeText(context, "Successful Message", Toast.LENGTH_LONG).show()
            }else{
                ActivityCompat.requestPermissions(context as Activity, arrayOf(permissao), 0)
            }
        }catch (e: Exception){
            ActivityCompat.requestPermissions(context as Activity, arrayOf(), 0)
        }
    }
}