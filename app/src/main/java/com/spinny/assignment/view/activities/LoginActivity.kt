package com.spinny.assignment.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.spinny.assignment.AppController
import com.spinny.assignment.databinding.ActivityMainBinding
import com.spinny.assignment.model.entities.Auth
import com.spinny.assignment.utilities.actionIntent
import com.spinny.assignment.utilities.toast
import com.spinny.assignment.view.base.BaseActivity
import com.spinny.assignment.viewmodel.AuthViewModel
import com.spinny.assignment.viewmodel.AuthViewModelFactory

class LoginActivity : BaseActivity<ActivityMainBinding>() {

    private val wordViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory((application as AppController).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(sessionConfig.getLoginStatus()) {
            actionIntent(DashboardActivity::class.java)
            finish()
        }
        bind.signupButton.setOnClickListener { actionIntent(SignUpActivity::class.java) }
        bind.loginButton.setOnClickListener { loginAction() }

    }

    private fun loginAction() {
        if (bind.usernameEditText.text.isBlank()) return toast("Enter user name")
        if (bind.passwordEditText.text.isBlank()) return toast("Enter password")

        val auth =
            Auth(0, bind.usernameEditText.text.toString(), bind.passwordEditText.text.toString())

        wordViewModel.getAuthDetails(auth)

        wordViewModel.authDetails.observe(this) {
            if (it == null) {
                toast("User not exist!! Kindly SignUp")
            } else {
                sessionConfig.setLoginStatus(true, it.id)
                actionIntent(DashboardActivity::class.java)
                finish()
            }
        }


    }

    override fun getBinding() = ActivityMainBinding.inflate(layoutInflater)
}