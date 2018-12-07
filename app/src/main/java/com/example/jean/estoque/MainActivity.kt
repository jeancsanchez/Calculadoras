package com.example.jean.estoque

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.partial_resultado.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var visor = ""

    companion object {
        private const val CLEAR = 0
        private const val SOMAR = "+"
        private const val SUB = "-"
        private const val DIV = "/"
        private const val MULT = "*"
        private var operador = CLEAR
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configurarMenuLateral()

        btnUm.setOnClickListener { digitar(1) }
        btnDois.setOnClickListener { digitar(2) }
        btnTres.setOnClickListener { digitar(3) }
        btnQuatro.setOnClickListener { digitar(4) }
        btnCinco.setOnClickListener { digitar(5) }
        btnSeis.setOnClickListener { digitar(6) }
        btnSete.setOnClickListener { digitar(7) }
        btnOito.setOnClickListener { digitar(8) }
        btnNove.setOnClickListener { digitar(9) }
        btnZero.setOnClickListener { digitar(0) }

        btnSoma.setOnClickListener { digitar(SOMAR) }
        btnSub.setOnClickListener { digitar(SUB) }
        btnMult.setOnClickListener { digitar(MULT) }
        btnIgual.setOnClickListener { mostrarResultado() }
        btnClear.setOnClickListener {
            txtResultado.text = "0"
            visor = "0"
        }
    }


    override fun onBackPressed() {
        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        val id = item.itemId

        when (id) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


    private fun configurarMenuLateral() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        val toggle = ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }


    private fun digitar(digito: Any) {
        if (visor == "0") {
            visor = ""
        }

        visor = visor.plus(digito.toString())
        txtResultado.text = visor
    }

    private fun mostrarResultado() {
        var total: Long = 0

        if (SOMAR in visor) {
            val valores = visor.split(SOMAR)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() + valores[index + 1].toLong()
                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (SUB in visor) {
            val valores = visor.split(SUB)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() - valores[index + 1].toLong()
                    } catch (e: java.lang.IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        if (MULT in visor) {
            val valores = visor.split(MULT)
            valores.forEachIndexed { index, item ->
                if (item.isEmpty().not()) {
                    try {
                        total += item.toLong() * valores[index + 1].toLong()
                    } catch (e: IndexOutOfBoundsException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        visor = total.toString()
        txtResultado.text = visor
    }
}
