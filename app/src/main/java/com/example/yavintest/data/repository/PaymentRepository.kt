package com.example.yavintest.data.repository

import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.WorkerThread
import com.example.yavintest.BuildConfig
import com.example.yavintest.data.dao.TicketDao
import com.example.yavintest.data.entity.Ticket
import com.example.yavintest.data.entity.TicketType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.json.JSONArray
import org.json.JSONException
import javax.inject.Inject

class PaymentRepository @Inject constructor(private val ticketDao: TicketDao) {

    fun getTickets(): Flow<List<Ticket>> {
        return ticketDao.getTickets().map {
            if (it.isEmpty()) {
                for (type in TicketType.values()) {
                    insert(Ticket(0, type.price, type))
                }
            }
            it
        }
    }

    suspend fun updateTicketPrice(price: Double, id: Int) {
        ticketDao.update(price, id)
    }

    @WorkerThread
    suspend fun insert(ticket: Ticket) {
        ticketDao.insert(ticket)
    }

    fun startPaymentOnYavinPay(
        amount: Double,
        resultLauncher: ActivityResultLauncher<Intent>
    ) {
        val intent = Intent()
        intent.component =
            ComponentName("com.yavin.macewindu", "com.yavin.macewindu.PaymentActivity")
        intent.putExtra("vendorToken", BuildConfig.API_KEY)
        intent.putExtra("amount", amount.toString())
        intent.putExtra("currency", "EUR")
        intent.putExtra("transactionType", "Debit")
        intent.putExtra("reference", "12345")
        intent.putExtra("client", "{\"phone\":\"0611223344\",\"email\":\"leo.rougier@gmail.com\"}")
        val jArray = JSONArray("[\"hello printer\", \"this is a\", \" wonderful\", \" TICKET\"]")
        val receiptTicket = ArrayList<String>()
        for (i in 0 until jArray.length()) {
            try {
                receiptTicket.add(jArray.getString(i))
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        intent.putExtra("receiptTicket", receiptTicket)
        try {
            resultLauncher.launch(intent)
        } catch (e: ActivityNotFoundException) {

        }
    }
}