package com.example.transitions

import android.content.Intent
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment

class FragmentA : Fragment() {
    private lateinit var imageViewA: ImageView
    private lateinit var textViewA: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        imageViewA = view.findViewById(R.id.imageViewA)
        textViewA = view.findViewById(R.id.textViewA)

        val buttonToFragmentB = view.findViewById<Button>(R.id.buttonToFragmentB)
        buttonToFragmentB.setOnClickListener {
            exitTransition = TransitionSet().apply {
                addTransition(Slide())
                addTransition(Fade())
            }

            val fragmentB = FragmentB().apply {
                enterTransition = TransitionSet().apply {
                    addTransition(Slide())
                    addTransition(Fade())
                }
            }

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragmentB)
                .addToBackStack(null)
                .commit()
        }

        val buttonAnimate = view.findViewById<Button>(R.id.buttonAnimate)
        buttonAnimate.setOnClickListener {
            animateViews()
        }

        imageViewA.setOnClickListener { clickedView ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("IMAGE_ID", R.drawable.goku_base)

            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                Pair.create(clickedView, "sharedImage")
            )
            startActivity(intent, options.toBundle())
        }

        return view
    }

    private fun animateViews() {
        val changeBounds = ChangeBounds().apply {
            duration = 300
        }

        TransitionManager.beginDelayedTransition(view as ViewGroup, changeBounds)

        imageViewA.layoutParams.height += 50
        imageViewA.layoutParams.width += 50
        textViewA.translationY += 20f
        imageViewA.requestLayout()
    }
}