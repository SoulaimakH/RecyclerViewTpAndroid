package com.gl4.myapplicationtp2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.myapplicationtp2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var matieres = listOf<String>("Cours","TP")

    var studients = ArrayList<Student>()

    fun CreerMesLigne() :ArrayList<Student>
    {
        val studients = ArrayList<Student>()
        studients.add( Student("VIGAN","Joel","h"))
        studients.add( Student("p2","Smith","h"))
        studients.add( Student("p3","Prm","f"))
        studients.add( Student("p4","kiven","h"))
        return studients
    }
    lateinit var monRecycler: RecyclerView
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(
                    applicationContext, matieres.get(position),
                    Toast.LENGTH_LONG
                ).show()
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }


        studients=CreerMesLigne();
        monRecycler = findViewById(R.id.recyclerView)
        monRecycler.layoutManager = LinearLayoutManager(this)
        monRecycler.adapter = studentAdapter(studients.toTypedArray())
        {
            Toast.makeText(this,"Vous avez sélectionné",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}