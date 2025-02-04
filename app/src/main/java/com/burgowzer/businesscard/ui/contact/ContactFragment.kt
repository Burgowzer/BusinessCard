package com.burgowzer.businesscard.ui.contact

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.burgowzer.businesscard.R

class ContactFragment : Fragment() {

    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_contact, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        contactViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })


        return root
    }
}