package com.example.myproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main_registro.*
import kotlinx.android.synthetic.main.content_main_registro.*

class MainRegistro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_registro)
        setSupportActionBar(toolbar)

        btnGuardar.setOnClickListener { view ->
            if (txtCorreo.text.isEmpty() || txtNombre.text.isEmpty() || txtContrasena.text.isEmpty()) {
                txtCorreo.setError("Error! Ingrese todos los Datos Requeridos")
                txtCorreo.requestFocus()
            } else {
                val correo = txtCorreo.text.toString()
                val nombre = txtNombre.text.toString()
                val contrasena = txtContrasena.text.toString()
                val sentencia =
                    "INSERT INTO usuario (correoUser, nombreUser, contrasenaUser) VALUES ('$correo', '$nombre', '$contrasena')"
                val admin = adminBD(this)
                if (admin.Ejecutar(sentencia) == 1) {
                    Toast.makeText(this, "Usuario Reguistrado", Toast.LENGTH_SHORT).show()
                    val actHome: Intent = Intent(this, MainActivity::class.java)
                    actHome.putExtra(MainActivity.EXTRA_CORREO, correo)
                    actHome.putExtra(MainActivity.EXTRA_CONTRASENA, contrasena)
                    startActivity(actHome)
                } else {
                    Toast.makeText(this, "Usuario no Agregado", Toast.LENGTH_SHORT).show()
                    txtCorreo.setText("")
                    txtNombre.setText("")
                    txtCorreo.requestFocus()
                }
            }
        }
    }
}
