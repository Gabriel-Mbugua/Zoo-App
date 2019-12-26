package com.gabriel.zooapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animal_info.*

class AnimalInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_info)

        val bundle:Bundle = intent.extras!!
        val name = bundle.getString("name")
        val description = bundle.getString("description")
        val image = bundle.getInt("image")
        val predator = bundle.getBoolean("type")

        txtName.text = name
        txtDescription.text = description
        imageViewAnimal.setImageResource(image)
        txtType.text = if(predator){"Predator"}else{"Herbivore"}
    }


}
