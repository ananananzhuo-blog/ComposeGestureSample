package com.ananananzhuo.composegesturesample

import android.app.Application
import android.content.Context
import android.widget.Toast

/**
 * author  :mayong
 * function:
 * date    :2021/12/19
 **/
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        app=this
    }
    companion object{
        lateinit var app:App
        fun toast(msg:String){
            Toast.makeText(app.applicationContext,msg,Toast.LENGTH_LONG).show()
        }
    }
}