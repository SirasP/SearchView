package com.example.rview


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso


class Detalles_retrofit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_retrofit)


        val img_detalles = findViewById<ImageView>(R.id.img_detalles)
        //val id_detalles = findViewById<TextView>(R.id.tvId_detalles)
        val direccion_detalles = findViewById<TextView>(R.id.tvDireccion_detalles)

        val intent = intent

        val img = intent?.getStringExtra("ruta_imagen")
        val id = intent?.getIntExtra("id", 0)
        val direccion = intent?.getStringExtra("dir")
        // https://photos.google.com/album/AF1QipPrmxkMj4BcNecbAbJvGMdGqa02wPU0j6rEK3fb/photo/AF1QipMv5RWFffHllAmtaPU6S7yAbpNroMfRxkY9kaKW

        Picasso.get()
            .load("https://2629-190-121-3-146.sa.ngrok.io/LomosyLomillos/imagenLomos/" + img)
            .centerCrop()

            .resize(1000, 1000).into(img_detalles)

       // id_detalles.text = id.toString()
        direccion_detalles.text = direccion


    }
}