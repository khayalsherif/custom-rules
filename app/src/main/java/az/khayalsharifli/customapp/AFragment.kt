package az.khayalsharifli.customapp

import android.os.Bundle
import az.khayalsharifli.customapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

class AFragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        emptyFun()
    }

    private fun emptyFun() {
        println("Work")
    }
}