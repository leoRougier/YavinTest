package com.example.yavintest.ui.main

import android.content.Intent
import android.text.Editable
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.*
import com.example.yavintest.data.entity.Ticket
import com.example.yavintest.data.repository.PaymentRepository
import com.example.yavintest.ui.component.TicketViewItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PaymentRepository) : ViewModel() {

    val getTicket: LiveData<List<Ticket>> = repository.getTickets().asLiveData()

    fun insert(ticket: Ticket) = viewModelScope.launch {
        repository.insert(ticket)
    }

    fun updateTicketPrice(text: Editable, id: Int) = viewModelScope.launch {
        repository.updateTicketPrice(text.toString().toDouble(), id)
    }

    fun proceedToPayment(
        items: List<TicketViewItem>?,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        var total = 0.0
        items?.forEach { item ->
            if (item.data.total > 0) {
                total += item.data.price
            }
        }
        if (total > 0.0){
            repository.startPaymentOnYavinPay(total, resultLauncher)
        }
    }
}

