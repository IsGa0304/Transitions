package com.example.transitions

import android.os.Bundle
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentB : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_b, container, false)

        val buttonToFragmentA = view.findViewById<Button>(R.id.buttonToFragmentA)
        buttonToFragmentA.setOnClickListener {
            exitTransition = TransitionSet().apply {
                addTransition(Slide())
                addTransition(Fade())
            }

            val fragmentA = FragmentA().apply {
                enterTransition = TransitionSet().apply {
                    addTransition(Slide())
                    addTransition(Fade())
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentA)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}