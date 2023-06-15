package com.example.notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.notesapp.database.NotesDatabase
import com.example.notesapp.databinding.FragmentCreateNoteBinding
import com.example.notesapp.databinding.FragmentHomeBinding
import com.example.notesapp.entities.Notes
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class CreateNoteFragment : BaseFragment() {
    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    var currentDate:String? = null
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
        currentDate = sdf.format(Date())

        binding.tvDateTime.text = currentDate

        binding.imgDone.setOnClickListener {
            //saveNote
            saveNote()
        }

        binding.imgBack.setOnClickListener {
            replaceFragment(HomeFragment.newInstance(),false)
        }
    }

    private fun saveNote() {

        if(binding.etNoteTitle.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Title is Required", Toast.LENGTH_SHORT).show()
        }

        if(binding.etNoteSubTitle.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Sub Title is Required", Toast.LENGTH_SHORT).show()
        }

        if(binding.etNoteDesc.text.isNullOrEmpty()){
            Toast.makeText(context, "Note Description is Required", Toast.LENGTH_SHORT).show()
        }

        launch {
            var notes = Notes()
            notes.title = binding.etNoteTitle.text.toString()
            notes.subTitle = binding.etNoteSubTitle.text.toString()
            notes.noteText = binding.etNoteDesc.text.toString()
            notes.dateTime = currentDate

            context?.let {
                NotesDatabase.getDatabase(it).noteDao().insertNotes(notes)
                binding.etNoteTitle.setText("")
                binding.etNoteSubTitle.setText("")
                binding.etNoteDesc.setText("")
            }
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