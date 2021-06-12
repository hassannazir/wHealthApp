package com.wHealth.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.wHealth.R
import com.wHealth.activities.forgotpassword.ForgotPasswordActivity
import com.wHealth.activities.login.LoginActivity
import com.wHealth.activities.ui.allClinics.AllClinicFragment
import com.wHealth.activities.ui.bookedappointments.BookedAppointmentsAdapter
import com.wHealth.activities.ui.bookedappointments.BookedAppointmentsFragment
import com.wHealth.activities.ui.doctor.DoctorFragment
import com.wHealth.activities.ui.clinics.ClinicFragment
import com.wHealth.activities.ui.doctorsrequest.DoctorRequestFragment
import com.wHealth.activities.ui.feedbacklist.FeedbackListFragment
import com.wHealth.activities.ui.removeclinic.RemoveClinicFragment
import com.wHealth.sharedpreferences.WHealthSharedPreference
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private  val sharedPreference:WHealthSharedPreference by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user=sharedPreference.getCurrentUser()

        val navView: NavigationView = findViewById(R.id.nav_view)
        val headerView=navView.getHeaderView(0)
        headerView.loggedinUserName.text=user.username
        headerView.loggedinUserEmail.text=user.email
        if(user?.type == "Doctor")
        {
            toolbar.title = "Main"
            loadFragment(DoctorFragment())
        }
        else if(user?.type == "Clinic"){
            toolbar.title = "Main"
            loadFragment(ClinicFragment())
        }
        else if(user?.type == "Patient"){
            toolbar.title = "Main"
            loadFragment(AllClinicFragment())
        }
        //View Profile
        headerView.loggedinUserName.setOnClickListener{

            moveToProfileActivity()
        }
        showNavigationDrawer()
        val navigationView =
            findViewById<View>(R.id.nav_view) as NavigationView
        val nav_Menu: Menu = navigationView.getMenu()
        nav_view.getMenu().getItem(0).isChecked = true
        if(user.type=="Doctor")
        {
            nav_Menu.findItem(R.id.nav_workingDoctors).setVisible(false);
            nav_Menu.findItem(R.id.nav_newRequests).setVisible(false);
            nav_Menu.findItem(R.id.nav_removeClinic).setVisible(false)
        }
        if(user.type=="Clinic")
        {
            nav_Menu.findItem(R.id.nav_allClinics).setVisible(false);
            nav_Menu.findItem(R.id.nav_workingClinics).setVisible(false);
            nav_Menu.findItem(R.id.nav_pendingappointments).setVisible(false);
            nav_Menu.findItem(R.id.nav_bookappointments).setVisible(false)
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false)
        }
        if(user.type=="Patient")
        {
            nav_Menu.findItem(R.id.nav_workingClinics).setVisible(false);
            nav_Menu.findItem(R.id.nav_workingDoctors).setVisible(false);
            nav_Menu.findItem(R.id.nav_newRequests).setVisible(false);
            nav_Menu.findItem(R.id.nav_bookappointments).setVisible(false)
            nav_Menu.findItem(R.id.nav_feedback).setVisible(false)
            nav_Menu.findItem(R.id.nav_removeClinic).setVisible(false)
        }

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_workingDoctors ->{
                    loadFragment(ClinicFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_newRequests -> {
                    loadFragment(DoctorRequestFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_allClinics -> {
                    loadFragment(AllClinicFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_workingClinics -> {
                    loadFragment(DoctorFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_pendingappointments -> {
                    loadFragment(BookedAppointmentsFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_bookappointments -> {
                    loadFragmentExtra(BookedAppointmentsFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_feedback -> {
                    loadFragmentExtra(FeedbackListFragment())
                    closeDrawer()
                    true
                }
                R.id.nav_removeClinic -> {
                    loadFragmentExtra(RemoveClinicFragment())
                    closeDrawer()
                    true
                }
                else -> {
                    false
                }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            // User chose the "Settings" item, show the app settings UI...
            sharedPreference.removeUserToken()
            sharedPreference.clearSharedPreference()
            Toast.makeText(this, "Successfully Logout.", Toast.LENGTH_SHORT).show()
            moveToLoginActivity()
            true
        }



        R.id.action_change_pass->{
          startActivity(   Intent(MainActivity@this, ForgotPasswordActivity::class.java).apply {
                  putExtra(IS_FROM_MAIN, true)
              }
          )
            true
        }
        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragment)?.addToBackStack("")
            .commit()
    }
    private fun loadFragmentExtra(fragment: Fragment) {
        var data:Bundle=Bundle()
        data.putString("name","booked");
        var fragmentmove:Fragment=fragment
        fragmentmove.setArguments(data);
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, fragmentmove)?.addToBackStack("")
            .commit()

    }
    private fun moveToLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun closeDrawer() {
        drawer_layout.closeDrawer(GravityCompat.START)
    }
    private fun showNavigationDrawer() {
        setSupportActionBar(toolbar)
        val drawerToggle: androidx.appcompat.app.ActionBarDrawerToggle =
            object : androidx.appcompat.app.ActionBarDrawerToggle(
                this,
                drawer_layout,
                toolbar,
                (R.string.navigation_drawer_open),
                (R.string.navigation_drawer_close)
            ) {

            }
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }
    private fun moveToProfileActivity() {
        startActivity(Intent(this, ProfileActivity::class.java))

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    companion object {
        const val IS_FROM_MAIN = "fromMaiin"
    }
}