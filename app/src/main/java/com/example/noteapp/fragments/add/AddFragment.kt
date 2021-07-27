package com.example.noteapp.fragments.add


import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.example.noteapp.databinding.FragmentAddBinding



class AddFragment : Fragment() {

    private var _binding : FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mNoteViewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       _binding = FragmentAddBinding.inflate(inflater, container, false)
       val view = binding.root

        mNoteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        setHasOptionsMenu(true)
       return view
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu,menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.add_note){
           insertDataToDataBase()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun insertDataToDataBase() {
        // getting data from input-text field
        val firstName = binding.addTitleEt.text.toString()
        val lastName  = binding.addBodyEt.text.toString()

        if (inputCheck(firstName,lastName)){
            //create Note Object
            val note = Note(0,firstName,lastName)
            //add data to database
            mNoteViewModel.addNote(note)
            Toast.makeText(requireContext(),"Successfully added!",Toast.LENGTH_SHORT).show()
            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields.",Toast.LENGTH_SHORT).show()
        }
    }

    //check if is empty return false
    private fun inputCheck(firstName:String,lastName:String):Boolean{
        return !(TextUtils.isEmpty(firstName)
                && TextUtils.isEmpty(lastName))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}