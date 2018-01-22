package com.johnny.demo.drawerWithTabs.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.BindView
import butterknife.ButterKnife
import com.johnny.demo.drawerWithTabs.DetailActivity
import com.johnny.demo.drawerWithTabs.R

/**
 * Created by johnny on 22/01/2018.
 */
class Nav01Tab1Fragment : Fragment() {

    @BindView(R.id.button_detail)
    lateinit var buttonDetail: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nav01_tab1, container, false)
        ButterKnife.bind(this, view)

        buttonDetail.setOnClickListener {
            activity?.let { activity ->
                val intent = Intent(activity, DetailActivity::class.java)
                activity.startActivity(intent)
            }
        }
        return view
    }
}