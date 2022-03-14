package com.dev.androidarchitectures.mvvm


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.androidarchitectures.CountriesAdapter
import com.dev.androidarchitectures.R
import com.dev.androidarchitectures.data.model.Countries

class MVVMActivity : AppCompatActivity(), CountriesAdapter.OnClick {

    var list: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var progressBar : ProgressBar
    lateinit var retryButton: Button
    lateinit var viewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvvm)

        viewModel = ViewModelProvider
             .AndroidViewModelFactory.getInstance(application)
             .create(CountriesViewModel::class.java)
        recyclerView  = findViewById(R.id.list_countries)
        progressBar  = findViewById(R.id.progress_bar)
        retryButton  = findViewById(R.id.button_retry)
        setAdaper()
        retryButton.setOnClickListener{
            onRetry()
        }
        viewModel.listLiveData.observe(this, Observer<List<Countries>> {
            if(it != null){
                var dataList = ArrayList<String>()
                for (i in it.indices){
                    dataList.add(it[i].countriesName.countryName!!)
                }
                onSuccess(dataList)
            }

        })

        viewModel.errorLiveData.observe(this, Observer<Boolean> {
            if (it){
                onError()
            }
        })
    }

    private fun setAdaper() {
        countriesAdapter = CountriesAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = countriesAdapter
    }

     private fun onSuccess(datalist: ArrayList<String>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.GONE
        countriesAdapter.clearData()
        list.addAll(datalist)
        countriesAdapter.notifyDataSetChanged()
    }

     private fun onError() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
    }

    fun onRetry(){
        retryButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        viewModel.onRetry()
    }

    override fun setOnItem(position: Int, arr: ArrayList<String>) {
        Toast.makeText(this, "${arr[position]}, clicked", Toast.LENGTH_LONG).show()
    }

}