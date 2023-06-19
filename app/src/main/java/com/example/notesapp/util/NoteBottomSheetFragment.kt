package com.example.notesapp.util

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNotesBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NoteBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentNotesBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)

        val view = LayoutInflater.from(context).inflate(R.layout.fragment_notes_bottom_sheet, null)
        dialog.setContentView(view)

        val param = (view.parent as View).layoutParams as CoordinatorLayout.LayoutParams

        val behavior = param.behavior

        if (behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    var state = ""
                    when (newState) {
                        BottomSheetBehavior.STATE_DRAGGING -> {
                            state = "DRAGGING"
                        }

                        BottomSheetBehavior.STATE_SETTLING -> {
                            state = "SETTLING"
                        }

                        BottomSheetBehavior.STATE_EXPANDED -> {
                            state = "EXPANDED"
                        }

                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            state = "COLLAPSED"
                        }

                        BottomSheetBehavior.STATE_HIDDEN -> {
                            state = "HIDDEN"
                            dismiss()
                            behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /* Original: return super.onCreateView(inflater, container, savedInstanceState)
        Change to:*/
        _binding = FragmentNotesBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

}