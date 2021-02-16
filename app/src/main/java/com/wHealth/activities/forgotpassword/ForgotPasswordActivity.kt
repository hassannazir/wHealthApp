package com.wHealth.activities.forgotpassword

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.Observer
import com.wHealth.R
import com.wHealth.activities.BaseActivity
import com.wHealth.activities.MainActivity
import com.wHealth.activities.login.LoginActivity
import com.wHealth.activities.login.LoginViewModel
import com.wHealth.di.activityScope
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_new_password.*
import kotlinx.android.synthetic.main.activity_verify_code.*
import kotlinx.android.synthetic.main.app_bar_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.scope.viewModel
import kotlin.properties.Delegates

class ForgotPasswordActivity : BaseActivity() {
    private  val sharedPreference: WHealthSharedPreference by inject()
    private val viewModel: ForgotPasswordViewModel by activityScope.viewModel(this)
    private var verCode: String? = " "
    private var verUser: String = " "
    var isFromMain: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_forgot_password)
        isFromMain = intent.getBooleanExtra(MainActivity.IS_FROM_MAIN, false)

        if(isFromMain) showNewPassLayout()

        emailSend.setOnClickListener {
            validateUsername()
        }

        viewModel.verifySuccessLiveData.observe(this, Observer { response->
            if(response.status)
            {
                Toast.makeText(this,"Email Sent.", Toast.LENGTH_SHORT).show()
                verCode=response.result
                showVerifyCodeLayout()
            }
            else
            {
                Toast.makeText(this, response.result, Toast.LENGTH_SHORT).show()
            }
            //signUpProgressBar.hide()
        })

        verifyCode.setOnClickListener {
            validateCode()
        }


        updatePass.setOnClickListener {
            validateNewPass()
        }

        viewModel.newPassSuccessLiveData.observe(this, Observer { res->

            if(res.status)
            {
                Toast.makeText(this, res.result, Toast.LENGTH_SHORT).show()
                if(!isFromMain) moveToLoginActivity()
                else finish()
            }
            else
            {
                Toast.makeText(this, res.result, Toast.LENGTH_SHORT).show()
            }

        })

    }

    private fun moveToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showVerifyCodeLayout()
    {
        forgetPasswordMainLayout.visibility=GONE
        verifyCodeLayout.visibility= VISIBLE
        newPasswordLayout.visibility=GONE
    }

    private fun showNewPassLayout()
    {
        forgetPasswordMainLayout.visibility=GONE
        verifyCodeLayout.visibility= GONE
        newPasswordLayout.visibility= VISIBLE

    }

    private fun showMainLayout()
    {
        forgetPasswordMainLayout.visibility= VISIBLE
        verifyCodeLayout.visibility= GONE
        newPasswordLayout.visibility=GONE

    }

    private fun validateUsername() {

        val username: String = resetPass.text.toString().trim()

        if (username.isEmpty()) {
            resetPass.error = "User Name Required"
            resetPass.requestFocus()
            return
        }

        verUser=username


        viewModel.verifyUser(username)
    }

    private fun validateCode() {

        val vCode: String = code.text.toString().trim()

        if (vCode.isEmpty()) {
            code.error = "Code Required"
            code.requestFocus()
            return
        }

        if (vCode!=verCode) {
            code.error = "Code Invalid."
            code.requestFocus()
            return
        }
        Toast.makeText(this, "Code Verified.", Toast.LENGTH_SHORT).show()
        showNewPassLayout()

                               }

    private fun validateNewPass()
    {

        val nPass: String = newPass.text.toString().trim()
        val cPass: String = confirmNewPass.text.toString().trim()

        if (nPass.isEmpty()) {
            newPass.error = "New Password Required"
            newPass.requestFocus()
            return
        }

        if (cPass.isEmpty()) {
            confirmNewPass.error = "Confirm New Password Required"
            confirmNewPass.requestFocus()
            return
        }

        if(nPass!=cPass)
        {
            confirmNewPass.error = "Please Enter Same Password."
            confirmNewPass.requestFocus()
            return
        }
if(isFromMain) {
    verUser = sharedPreference.getCurrentUser().username
}
        viewModel.UpdatePass(verUser,nPass)

    }

}