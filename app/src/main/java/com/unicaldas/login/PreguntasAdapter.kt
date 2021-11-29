package com.unicaldas.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.unicaldas.login.model.Pregunta
import kotlinx.android.synthetic.main.preguntas_list_item.view.*

class PreguntasAdapter(private val mContext: Context, val listaPreguntas: List<Pregunta>)
    : ArrayAdapter<Pregunta>( mContext, 0, listaPreguntas ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.preguntas_list_item,parent,false)
        val pregunta = listaPreguntas[position]
        layout.areaPregunta.text = pregunta.area
        layout.descripcionPregunta.text = pregunta.preguntaTexto

        return layout
    }
}