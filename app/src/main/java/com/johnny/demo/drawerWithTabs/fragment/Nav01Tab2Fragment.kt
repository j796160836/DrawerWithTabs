package com.johnny.demo.drawerWithTabs.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.johnny.demo.drawerWithTabs.R

/**
 * Created by johnny on 22/01/2018.
 */
class Nav01Tab2Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nav01_tab2, container, false)
        ButterKnife.bind(this, view)

        return view
    }
}