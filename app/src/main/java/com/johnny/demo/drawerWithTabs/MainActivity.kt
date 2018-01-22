package com.johnny.demo.drawerWithTabs

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.annotation.MenuRes
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.GravityCompat
import android.support.v4.view.PagerAdapter
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import com.johnny.demo.drawerWithTabs.fragment.*
import com.johnny.demo.drawerWithTabs.view.NonSwipeableViewPager
import java.util.*

class MainActivity : AppCompatActivity() {

    @BindView(R.id.drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.view_pager)
    lateinit var viewPager: NonSwipeableViewPager

    @BindView(R.id.tab_layout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.navigation_view)
    lateinit var navigationView: NavigationView

    @BindView(R.id.button_fab)
    lateinit var buttonFAB: FloatingActionButton

    private var adapter: Adapter? = null

    @MenuRes
    private var selectedMenuId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!resources.getBoolean(R.bool.isTablet)) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
            it.setDisplayHomeAsUpEnabled(true)
        }

        setupFABButton()
        setupDrawerContent()
        setupViewPager()
        updateViewPagerFragment()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("selectedMenuId", selectedMenuId)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.getInt("selectedMenuId", -1)?.let {
            updateViewPagerFragment(it)
        }
    }

    private fun setupFABButton() {
        buttonFAB.setOnClickListener { view ->
            Snackbar.make(view, R.string.snackbar_txt, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action, null).show()
        }
    }

    private fun setupViewPager() {
        adapter = Adapter(supportFragmentManager)
        fillTab01()
        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setupDrawerContent() {
        navigationView.setNavigationItemSelectedListener { menuItem ->
            updateViewPagerFragment(menuItem.itemId)
            menuItem.isChecked = true
            drawerLayout.closeDrawers()
            return@setNavigationItemSelectedListener true
        }
    }

    private fun updateViewPagerFragment(@MenuRes forMenuItemId: Int = -1) {
        selectedMenuId = forMenuItemId
        when (forMenuItemId) {
            -1, R.id.nav_01 -> {
                adapter?.removeAllFragment()
                fillTab01()
                notifyUpdateViewpager()
                toolbar.title = getString(R.string.app_name)
                buttonFAB.hide()
            }
            R.id.nav_02 -> {
                adapter?.removeAllFragment()
                fillTab02()
                notifyUpdateViewpager()
                toolbar.title = getString(R.string.nav_02)
                buttonFAB.show()
            }
            R.id.nav_03 -> {
                adapter?.removeAllFragment()
                fillTab03()
                notifyUpdateViewpager()
                toolbar.title = getString(R.string.nav_03)
                buttonFAB.hide()
            }
        }
    }

    private fun fillTab01() {
        adapter?.addFragment(Nav01Tab1Fragment(), getString(R.string.nav_01_tab1))
        adapter?.addFragment(Nav01Tab2Fragment(), getString(R.string.nav_01_tab2))
        adapter?.addFragment(Nav01Tab3Fragment(), getString(R.string.nav_01_tab3))
    }

    private fun fillTab02() {
        adapter?.addFragment(Nav02Fragment(), getString(R.string.nav_02))
    }

    private fun fillTab03() {
        adapter?.addFragment(Nav03Fragment(), getString(R.string.nav_03))
    }

    private fun notifyUpdateViewpager() {
        adapter?.notifyDataSetChanged()
        tabLayout.visibility = if (adapter?.count == 1) View.GONE else View.VISIBLE
        viewPager.disableSwipe = (adapter?.count == 1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    internal class Adapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
        private val fragments = ArrayList<Fragment>()
        private val fragmentTitles = ArrayList<String>()

        fun addFragment(fragment: Fragment, title: String) {
            fragments.add(fragment)
            fragmentTitles.add(title)
        }

        fun removeAllFragment() {
            fragments.clear()
            fragmentTitles.clear()
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitles[position]
        }

        override fun getItemPosition(`object`: Any): Int {
            return PagerAdapter.POSITION_NONE
        }
    }
}
