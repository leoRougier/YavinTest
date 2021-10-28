package com.example.yavintest.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.yavintest.R
import com.example.yavintest.data.entity.Ticket
import com.example.yavintest.databinding.DialogBinding
import com.example.yavintest.databinding.MainFragmentBinding
import com.example.yavintest.ui.component.TicketViewAction
import com.example.yavintest.ui.component.TicketViewDatabinding
import com.example.yavintest.ui.component.TicketViewItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment(private val resultLauncher: ActivityResultLauncher<Intent>) : Fragment() {

    companion object {
        fun newInstance(resultLauncher: ActivityResultLauncher<Intent>) =
            MainFragment(resultLauncher)
    }


    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.getTicket.observe(this, {
            updateView(it)
        })

    }

    private fun updateView(ticket: List<Ticket>?) {
        val item = ticket?.map {
            TicketViewItem(TicketViewDatabinding(it), TicketViewAction {
                showCustomDialog(it)
            })
        }
        binding.data = item?.let { MainFragmentDatabinding(it) }
        binding.button.setOnClickListener {
            viewModel.proceedToPayment(item, resultLauncher)
        }
    }

    private fun showCustomDialog(ticket: Ticket) {
        val dialogBinding: DialogBinding? =
            DataBindingUtil.inflate(
                LayoutInflater.from(activity),
                R.layout.dialog,
                null,
                false
            )
        activity?.let {
            val customDialog = AlertDialog.Builder(it, 0).create()

            customDialog.apply {
                setView(dialogBinding?.root)
                setCancelable(false)
            }.show()

            dialogBinding?.btnOk?.setOnClickListener {
                viewModel.updateTicketPrice(dialogBinding.editTextNumberSigned.text, ticket.id)
                customDialog.dismiss()
            }
            dialogBinding?.btnCancel?.setOnClickListener {
                customDialog.dismiss()
            }
        }
    }
}