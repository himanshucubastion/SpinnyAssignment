package com.spinny.assignment.view.activities

import android.os.Bundle
import androidx.activity.viewModels
import com.spinny.assignment.AppController
import com.spinny.assignment.databinding.ActivitySignUpBinding
import com.spinny.assignment.model.entities.Auth
import com.spinny.assignment.utilities.actionIntent
import com.spinny.assignment.utilities.toast
import com.spinny.assignment.view.base.BaseActivity
import com.spinny.assignment.viewmodel.AuthViewModel
import com.spinny.assignment.viewmodel.AuthViewModelFactory

class SignUpActivity : BaseActivity<ActivitySignUpBinding>() {


    private val wordViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory((application as AppController).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind.loginButton.setOnClickListener { actionIntent(LoginActivity::class.java) }

        bind.signupButton.setOnClickListener {

            toast("Saving credentials in local storage")
            saveCreds()
        }
    }




    private fun saveCreds() {
        var auth =
            Auth(0, bind.usernameEditText.text.toString(), bind.passwordEditText.text.toString())

        wordViewModel.getAuthDetails(auth)

        wordViewModel.authDetails.observe(this) {
            if (it == null) {
                wordViewModel.createAuth(auth)
            } else {
                auth = it
                toast("User already exists")
            }
        }


    }

    override fun getBinding() = ActivitySignUpBinding.inflate(layoutInflater)


}