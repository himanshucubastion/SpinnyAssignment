package com.spinny.assignment.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.spinny.assignment.AppController
import com.spinny.assignment.db.SessionConfig

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var bind: T

    protected val sessionConfig: SessionConfig by lazy {
        SessionConfig(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = getBinding()
        setContentView(bind.root)
    }


    abstract fun getBinding(): T

}