package com.dev.androidarchitectures.mvp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.androidarchitectures.CountriesAdapter
import com.dev.androidarchitectures.R
import com.dev.androidarchitectures.mvc.Controller
import io.reactivex.disposables.CompositeDisposable

class MVPActivity : AppCompatActivity() , Presenter.View, CountriesAdapter.OnClick{

    var list: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var recyclerView : RecyclerView
    lateinit var progressBar : ProgressBar
    lateinit var retryButton: Button
    lateinit var presenter: Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvp)
        recyclerView  = findViewById(R.id.list_countries)
        progressBar  = findViewById(R.id.progress_bar)
        retryButton  = findViewById(R.id.button_retry)
        presenter = Presenter(this)
        setAdaper()
        retryButton.setOnClickListener{
            onRetry()
        }
    }

    private fun setAdaper() {
        countriesAdapter = CountriesAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = countriesAdapter
    }

    override fun onSuccess(datalist: ArrayList<String>) {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.GONE
        countriesAdapter.clearData()
        list.addAll(datalist)
        countriesAdapter.notifyDataSetChanged()
    }

    override fun onError() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.VISIBLE
    }

    fun onRetry(){
        retryButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        presenter.refresh()
    }

    override fun onDestroy() {
        super.onDestroy()
       presenter.onClear()
    }

    override fun setOnItem(position: Int, arr: ArrayList<String>) {
        Toast.makeText(this, "${arr[position]}, clicked", Toast.LENGTH_LONG).show()
    }


}