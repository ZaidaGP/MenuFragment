package cr.ac.menufragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Gravity
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

lateinit var drawerLayout : DrawerLayout
class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout=findViewById(R.id.drawer_layout)

        var toggle = ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var navegationView = findViewById<NavigationView>(R.id.design_navigation_view)
        navegationView.setNavigationItemSelectedListener(this)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment : Fragment

        when(item.itemId) {
            R.id.nav_camera -> fragment = CamaraFragment.newInstance(getString(R.string.menu_camera))
            R.id.nav_gallery -> fragment = GaleriaFragment.newInstance(getString(R.string.menu_gallery))

            else -> fragment = HomeFragment.newInstance("Home")
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.home_content,fragment)
            .commit()
            setTitle(R.string.homeFragment)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true

    }
}