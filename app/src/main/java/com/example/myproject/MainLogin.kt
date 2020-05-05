package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main_login.*
import kotlinx.android.synthetic.main.content_main_login.*

class MainLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_login)
        setSupportActionBar(toolbar)

        fbtnEntrar.setOnClickListener { view ->
            if (txtCorreo.text.isEmpty() || txtContrasena.text.isEmpty()) {
                txtCorreo.setError("Faltan Datos de Entrada")
                txtCorreo.requestFocus()
            } else {
                var Correo = txtCorreo.text.toString()
                var Contrasena = txtContrasena.text.toString()
                var admin = adminBD(this)
                val sentencia = "SELECT * FROM usuario WHERE correoUser = '$Correo' AND contrasenaUser = '$Contrasena'"
                val resultado = admin.Consulta(sentencia)
                if (resultado!!.moveToFirst()){
                    Correo = resultado.getString(0)
                    Contrasena = resultado.getString(2)
                    val actMain = Intent(this, MainActivity::class.java)
                    actMain.putExtra(MainActivity.EXTRA_CORREO, Correo)
                    actMain.putExtra(MainActivity.EXTRA_CONTRASENA, Contrasena)
                    startActivity(actMain)
                } else {
                    Toast.makeText(this, "¡Correo o Contraseña Invalido!", Toast.LENGTH_SHORT).show()
                    txtCorreo.requestFocus()
                }
            }
        }
    }

    fun fbtnRegistrar_Click(view: View) {
        val actResg: Intent = Intent(this, MainRegistro::class.java)
        startActivity(actResg)
    }

}
