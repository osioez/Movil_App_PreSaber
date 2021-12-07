package com.unicaldas.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.unicaldas.login.model.Test
import kotlinx.android.synthetic.main.preguntas_list_item.view.*

class TestAdapter(private val mContext: Context, val listaTest: List<Test>)
    : ArrayAdapter<Test>( mContext, 0, listaTest ){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContext).inflate(R.layout.preguntas_list_item,parent,false)
        val test = listaTest[position]
        layout.areaPregunta.text = test.id.toString()
        layout.descripcionPregunta.text = test.testTexto

        return layout
    }
}