package com.example.rview.retrofityrecycl2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

import androidx.recyclerview.widget.RecyclerView
import com.example.rview.Detalles_retrofit
import com.example.rview.R
import com.squareup.picasso.Picasso


class PastAdapter(private var pastModel: MutableList<PastModel>) :
    RecyclerView.Adapter<PastViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PastViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return PastViewHolder(view)
    }

    override fun onBindViewHolder(holder: PastViewHolder, position: Int) {
        return holder.bindView(pastModel[position])

    }

    override fun getItemCount(): Int {
        return pastModel.size

    }

    fun onApplySearch(pastModel: MutableList<PastModel>) {
        this.pastModel = pastModel
        notifyDataSetChanged()

    }

}

class PastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tipo: TextView = itemView.findViewById(R.id.tvTitle)
    val titulo: TextView = itemView.findViewById(R.id.tvDescription)
    val img: ImageView = itemView.findViewById(R.id.imageView)
    val con: ConstraintLayout = itemView.findViewById(R.id.constraint_row)

    fun bindView(pastModel: PastModel) {

        tipo.text = pastModel.tipoLomo.toString()
        titulo.text = pastModel.direccion


        Picasso.get()
            .load("https://2629-190-121-3-146.sa.ngrok.io/LomosyLomillos/imagenLomos/" + pastModel.ruta_imagen)
            .centerCrop().resize(300, 300).into(img)


        val cont = con.context
        con.setOnClickListener {
            val intent = Intent(it.context, Detalles_retrofit::class.java)

            intent.putExtra("id", pastModel.tipoLomo)
            intent.putExtra("dir", pastModel.direccion)
            intent.putExtra("ruta_imagen", pastModel.ruta_imagen)

            it.context.startActivity(intent)

            Toast.makeText(cont, "${pastModel.direccion}", Toast.LENGTH_SHORT).show()
        }

    }


}