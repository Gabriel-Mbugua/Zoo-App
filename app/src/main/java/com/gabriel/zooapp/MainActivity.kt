package com.gabriel.zooapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*
import java.nio.file.Files.delete

class MainActivity : AppCompatActivity() {

    var listOfAnimals = arrayListOf<Animal>()
    var adapter: AnimalAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //load animals
        listOfAnimals.add(
            Animal(
                "Baboon",
                "Baboons can be found in the savanas",
                R.drawable.baboon,
                true
            )
        )
        listOfAnimals.add(
            Animal(
                "Panda",
                "Pandas are an endangered species",
                R.drawable.panda,
                false
            )
        )
        listOfAnimals.add(
            Animal(
                "Bulldog",
                "Bulldogs are know for being fierce",
                R.drawable.bulldog,
                true
            )
        )
        listOfAnimals.add(
            Animal(
                "Swallow bird",
                "Passerine birds found around the world on all continents",
                R.drawable.swallow_bird,
                false
            )
        )
        listOfAnimals.add(
            Animal(
                "White tiger",
                "A pigmentation variant of the Bengal tiger",
                R.drawable.white_tiger,
                true
            )
        )
        listOfAnimals.add(
            Animal(
                "Zebra",
                "Species of African equids united by their distinctive black-and-white striped coats",
                R.drawable.zebra,
                false
            )
        )

        adapter = AnimalAdapter(this, listOfAnimals)
        animalListView.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem) :Boolean = when(item.itemId){
        R.id.action_add ->{
            add(item.toString().toInt())
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }

    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index:Int){
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalAdapter : BaseAdapter {

        var listOfAnimals = arrayListOf<Animal>()
        var context: Context? = null

        constructor(context: Context, listOfAnimal: ArrayList<Animal>) : super() {
            this.context = context
            this.listOfAnimals = listOfAnimal
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]

            var inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var myView = inflater.inflate(R.layout.animal_ticket, null)
            myView.txtViewName.text = animal.name
            myView.txtViewDescription.text = animal.description
            myView.txtViewType.text = if (animal.predator == true) {
                "Predator"
            } else {
                "Herbivore"
            }
            myView.imageViewAnimal.setImageResource(animal.image!!)
            myView.imageViewAnimal.setOnClickListener{
                val intent = Intent(context, AnimalInfo::class.java)
                intent.putExtra("name", animal.name)
                intent.putExtra("description", animal.description)
                intent.putExtra("image", animal.image!!)
                val putExtra = intent.putExtra("type", animal.predator)
                context!!.startActivity(intent)
            }
            myView.deleteButton.setOnClickListener{
                delete(position)
            }
            return myView


        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }

}
