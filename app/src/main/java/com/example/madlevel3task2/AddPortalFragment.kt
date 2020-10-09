package com.example.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_portal.*


const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btAddPortal.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val titleText = etTitle.text.toString()
        val urlText = etTextUrl.text.toString()
        if (titleText.isBlank() && urlText.isBlank()) {
            Snackbar.make(etTitle, getString(R.string.warning_invalid_fields), Snackbar.LENGTH_LONG)
                .show()
        } else if (!etTextUrl.text.startsWith("http://") && !etTextUrl.text.startsWith("https://")) {
            Snackbar.make(
                etTitle,
                getString(R.string.warning_invalid_protocol),
                Snackbar.LENGTH_LONG
            )
                .show()
        } else {
            setFragmentResult(
                REQ_PORTAL_KEY,
                bundleOf(Pair(BUNDLE_PORTAL_KEY, Portal(titleText, urlText)))
            )
            findNavController().popBackStack()
        }
    }
}