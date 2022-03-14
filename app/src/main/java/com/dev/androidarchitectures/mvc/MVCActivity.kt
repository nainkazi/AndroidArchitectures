package com.dev.androidarchitectures.mvc


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
import io.reactivex.disposables.CompositeDisposable


class MVCActivity : AppCompatActivity(), CountriesAdapter.OnClick {

    var list: ArrayList<String> = ArrayList()
    lateinit var countriesAdapter: CountriesAdapter
    lateinit var recyclerView :RecyclerView
    lateinit var progressBar :ProgressBar
    lateinit var retryButton: Button
    lateinit var controller: Controller
    lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mvc)
        recyclerView  = findViewById(R.id.list_countries)
        progressBar  = findViewById(R.id.progress_bar)
        retryButton  = findViewById(R.id.button_retry)
        compositeDisposable = CompositeDisposable()
        controller = Controller(this@MVCActivity)
        setAdapter()

        retryButton.setOnClickListener{
           retry()
        }

    }

    private fun retry() {
        retryButton.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        controller.refresh()
    }

    private fun setAdapter() {
        countriesAdapter = CountriesAdapter(list, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = countriesAdapter
    }


    public fun onSuccess(nameslist: ArrayList<String>){
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
        retryButton.visibility = View.GONE
        countriesAdapter.clearData()
        list.addAll(nameslist)
        countriesAdapter.notifyDataSetChanged()
    }

   public fun onError(){
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
       retryButton.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    override fun setOnItem(position: Int, arr: ArrayList<String>) {
        Toast.makeText(this, "${arr[position]}, clicked",Toast.LENGTH_LONG).show()
    }
}