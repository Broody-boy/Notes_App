package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date

class CreateNoteFragment : Fragment() {
    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Original: return inflater.inflate(R.layout.fragment_create_note, container, false)
        Change to:*/
        _binding = FragmentCreateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateNoteFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sdf = SimpleDateFormat("dd/M/yyyyy hh:mm:ss")
        val currentDate = sdf.format(Date())

        binding.tvDateTime.text = currentDate

        binding.imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(),false)
        }
    }


    fun replaceFragment(fragment: Fragment, istransition: Boolean) {
        val fragmentTransition = requireActivity().supportFragmentManager.beginTransaction()

        if(istransition) {
            fragmentTransition.setCustomAnimations(android.R.anim.slide_out_right, android.R.anim.slide_in_left)
        }
        fragmentTransition.replace(R.id.frame_layout, fragment).addToBackStack(fragment.javaClass.simpleName).commit()
    }
}