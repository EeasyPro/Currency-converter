package com.example.modulbankhw5

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.collections.ArrayList


class RecyclerAdapter(private var currencyData: CurrencyResponse,
                      private var baseCurrencyAmount: BigDecimal,
                      private var context:Context
                      ) : RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>() {

    private var rates : LinkedList<CurrencyItemData>

    init {
        if (currencyData.base == "EUR") {
            currencyData.rates["EUR"] = baseCurrencyAmount
        }

        rates = LinkedList(currencyData.rates.map { CurrencyItemData(it.key, it.value) })
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ItemViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.fragment, viewGroup, false)
        return RecyclerAdapter(currencyData, baseCurrencyAmount, context).ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, i: Int) {
        holder.bind(rates[i])
    }

    override fun getItemCount(): Int {
        return rates.size
    }

    fun refreshData(currencyData: CurrencyResponse) {
        rates.clear()
        val mRates = currencyData.rates

        val newRates = ArrayList(mRates.map {CurrencyItemData(it.key, it.value)})
        for (item in newRates) {
            if (item.name == currencyData.base) {
                newRates.remove(item)
                break
            }
        }
        rates.addAll(newRates)
    }



    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currencyItemData: CurrencyItemData) {
            itemView.Valuta.text = currencyItemData.name
            itemView.Currency.setText((currencyItemData.value * baseCurrencyAmount).setScale(3,RoundingMode.HALF_DOWN).toEngineeringString())

            val imageName = "flag_" + currencyItemData.name.toLowerCase()
            val resId = context.getResources().getIdentifier(imageName , "drawable", context.getPackageName())
            itemView.imageView.setImageResource(resId)
        }

    }
}