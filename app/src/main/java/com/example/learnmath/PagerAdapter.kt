package com.example.learnmath

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount():Int= 2

    override fun createFragment(position:Int): Fragment {
        return when(position) {
            1 -> Calculo1()
            0 -> Calculo2()

            else-> throw IllegalStateException("Posição inválida: $position")
        }
    }
}