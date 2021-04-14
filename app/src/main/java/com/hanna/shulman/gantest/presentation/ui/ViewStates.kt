package com.hanna.shulman.gantest.presentation.ui

import android.view.View
import com.hanna.shulman.gantest.R

class ViewStates(val view: View) {

    private var currentState = State.UNKNOWN

    fun setState(state: State) {
        if (currentState == state) return

        currentState = state
        State.values().filterNot { it == state }.forEach {
            view.findViewById<View>(it.viewId)?.visibility = View.INVISIBLE
        }

        setLoadingIndicator(state == State.LOADING)
        view.findViewById<View>(state.viewId)?.visibility = View.VISIBLE
    }

    private fun setLoadingIndicator(isEnabled: Boolean) {

    }

    enum class State(val viewId: Int) {
        LOADING(R.id.loading_view),
        MAIN(R.id.main_view),
        ERROR(0),
        UNKNOWN(0),
    }
}