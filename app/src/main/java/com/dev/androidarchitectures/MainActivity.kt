package com.dev.androidarchitectures

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dev.androidarchitectures.mvc.MVCActivity
import com.dev.androidarchitectures.mvp.MVPActivity
import com.dev.androidarchitectures.mvvm.MVVMActivity
import java.lang.reflect.Array
import java.util.*
import java.util.Arrays.sort

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mvcButton: Button = findViewById(R.id.button_mvc)
        val mvpButton: Button = findViewById(R.id.button_mvp)
        val mvvmButton: Button = findViewById(R.id.button_mvvm)

        mvcButton.setOnClickListener {
            var intent = Intent(this, MVCActivity::class.java)
            startActivity(intent)
        }
        mvpButton.setOnClickListener {
            var intent = Intent(this, MVPActivity::class.java)
            startActivity(intent)
        }
        mvvmButton.setOnClickListener {
            var intent = Intent(this, MVVMActivity::class.java)
            startActivity(intent)
        }
       // println("${getSum(arrayOf(1,2,3,4,5,6,7,8,10,9), 10)}")
        checkOddEven()
    }

    fun getSum(arr: kotlin.Array<Int>, sum: Int):Boolean{
         var j = 0
         var r = 0
         Arrays.sort(arr)
        for(i in 0 until arr.size-2){

            j = i+1
            r = arr.size-1
            while (i < r){
                var checkCum = arr[i]+arr[j]+ arr[r]
                if(checkCum == sum){
                    println("Numbers = sum ${arr[i]} ${arr[j]} ${arr[r]}")
                    return true
                }else if(checkCum < sum){
                    j+=1
                }else{
                    r -= 1
                }
            }
           /* for(j in i+1 until arr.size){
                val twoSum = arr[i] + arr[j]
                for(k in j+1 until arr.size){
                    if((arr[k]+twoSum) == sum){
                        return true
                    }
                }
            }*/
        }
        return false
    }


    fun checkOddEven(){

        var cOdd = 0
        var cEven = 0
        var rOdd = ""
        var rEven = ""
        val arr = arrayOf(1,3,5,2,4,6,7,9,11,8,10,12)
        for(i in arr){

            if(i % 2 == 0){
                cOdd = 0
                cEven += 1
                rEven += "$i "
                rOdd = ""
            } else {
                cEven = 0
                cOdd += 1
                rOdd += "$i "
                rEven = ""
            }
            if(cEven == 3){
                println(rEven)
            }else if(cOdd == 3){
                println(rOdd)
            }
        }


    }
    }