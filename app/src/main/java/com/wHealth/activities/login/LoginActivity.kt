package com.wHealth.activities.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.register.RegisterActivity
import com.wHealth.di.activityScope
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.scope.viewModel
import androidx.lifecycle.Observer
import com.wHealth.activities.forgotpassword.ForgotPasswordActivity
import com.wHealth.activities.MainActivity

class LoginActivity : BaseActivity() {
    private val viewModel: LoginViewModel by activityScope.viewModel(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signupText.setOnClickListener {
            val i = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(i)
        }

        forgetPassText.setOnClickListener {
            val j = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
            startActivity(j)
        }

        login.setOnClickListener {
            validateAndLogin()
        }

        viewModel.loginSuccessLiveData.observe(this, Observer { response->this
            if(response.status)
            {
                Toast.makeText(this,"Successfully Logged in.", Toast.LENGTH_SHORT).show()
                moveToMainActivity()
            }
            else
            {
                Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
            }
            //signUpProgressBar.hide()
        })
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun validateAndLogin() {

        val uName: String = username.text.toString().trim()
        val pass: String = passWord.text.toString().trim()

        if (uName.isEmpty()) {
            username.error = "User Name Required"
            username.requestFocus()
            return
        }

        if (pass.isEmpty()) {
            passWord.error = "Password Required."
            passWord.requestFocus()
            return
        }

        viewModel.loginUser(uName,pass)
    }
}