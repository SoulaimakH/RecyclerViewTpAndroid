package com.gl4.myapplicationtp2

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gl4.myapplicationtp2.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity()  {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    var matieres = listOf<String>("Cours","TP")
    lateinit var mEdit : EditText;
    lateinit var button1 :Button
    var studients = ArrayList<Student>()

    fun CreerMesLigneCours() :ArrayList<Student>
    {
        val studients = ArrayList<Student>()
        studients.add( Student("sanoussi","Mouhamed","h"))
        studients.add( Student("Kahla","soulaima","f"))
        studients.add( Student("Nom2","Prm","f"))
        studients.add( Student("Nom3","kiven","h"))
        studients.add( Student("Tebolbi","Karim","h"))
        studients.add( Student("Moalla","Mariem","f"))
        studients.add( Student("Nom5","Ismahan","f"))
        studients.add( Student("Nom6","Karim","h"))
        return studients
    }
    fun CreerMesLigneTp() :ArrayList<Student>
    {
        val studients = ArrayList<Student>()
        studients.add( Student("Sdiri","Adem","h"))
        studients.add( Student("tekaya","aicha","f"))
        studients.add( Student("Nom2","bayrim","f"))
        studients.add( Student("Nom3","kiven","h"))
        studients.add( Student("Tebolbi","Karim","h"))
        studients.add( Student("Moalla","Mariem","f"))
        studients.add( Student("Nom5","Ismahan","f"))
        studients.add( Student("Nom6","Karim","h"))

        return studients
    }

    lateinit var monRecycler: RecyclerView
    private lateinit var Adapter: studentAdapter

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
        studients=CreerMesLigneCours();
        monRecycler = findViewById(R.id.recyclerView)
        monRecycler.layoutManager = LinearLayoutManager(this)
        mEdit = findViewById<View>(R.id.Search) as EditText


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if(matieres[position] =="Cours"){
                   studients=CreerMesLigneCours();
                   monRecycler.adapter =studentAdapter(studients)
               }

                else{
                   studients=CreerMesLigneTp();
                   monRecycler.adapter =studentAdapter(studients)
               }

            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }


    /*    button1=findViewById(R.id.button);

        button1.setOnClickListener(
            View.OnClickListener {

                val name = mEdit.text.toString()
                //Adapter.filter.filter("p2")
                studients=CreerMesLign();
                monRecycler.adapter =studentAdapter(studients)
            })*/




        mEdit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                monRecycler.adapter =studentAdapter(getFilter1(mEdit.text.toString()))
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })



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


    fun getFilter1(toString: String): ArrayList<Student> {
        var dataFilterList = studients
        if (toString.isEmpty()) {
           var dataFilterList = studients
        } else {
            val resultList = ArrayList<Student>()
            println(studients)
            for (student in studients) {
                if (student.nom.lowercase(Locale.ROOT)
                        .contains(toString.lowercase(Locale.ROOT))
                ) {
                    resultList.add(student)
                }
            }
            dataFilterList = resultList
            println(dataFilterList)
        }

        return dataFilterList;


    }

}