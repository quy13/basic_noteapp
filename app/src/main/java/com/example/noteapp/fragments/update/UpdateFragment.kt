package com.example.noteapp.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mNoteViewModel: NoteViewModel

    private var _binding : FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding =  FragmentUpdateBinding.inflate(inflater, container, false)
        val view = binding.root

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        binding.updateTitleEt.setText(args.currentNote.title)
        binding.updateBodyEt.setText(args.currentNote.body)


        binding.updateButton.setOnClickListener {
                updateItem()
        }
        //add menu
        setHasOptionsMenu(true)
        return view
    }


    private fun updateItem(){
        val firstName = binding.updateTitleEt.text.toString()
        val lastName = binding.updateBodyEt.text.toString()


        if (inputCheck(firstName, lastName)){
        //create Note Object
            val updatedNote = Note(args.currentNote.id,firstName,lastName)
        //update Current Note
            mNoteViewModel.updateNote(updatedNote)
            Toast.makeText(requireContext(),"Updated Successfully!",Toast.LENGTH_SHORT).show()
        //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(),"Please fill out all field.",Toast.LENGTH_SHORT).show()
        }
    }
    //check if is empty return false
    private fun inputCheck(firstName:String,lastName:String):Boolean{
        return !(TextUtils.isEmpty(firstName)
                && TextUtils.isEmpty(lastName))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
           deleteUser()
        }
        if(item.itemId == R.id.menu_add){
            updateItem()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes") { _, _->
            mNoteViewModel.deleteNote(args.currentNote)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentNote.title}.",
                Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${args.currentNote.title}?")
        builder.setMessage("Are you sure you want to delete ${args.currentNote.title}?")
        builder.create().show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}