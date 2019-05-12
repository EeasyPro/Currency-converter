package com.example.modulbankhw5

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.SpinnerAdapter
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment.*
import kotlinx.android.synthetic.main.fragment.view.*
import java.math.BigDecimal
import javax.inject.Inject

class CurrenciesScreen: MvpAppCompatActivity(),CurrenciesView {

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        refreshActivity.setOnRefreshListener {
            presenter.refresh(spinner.selectedItem.toString())
        }
        getSupportActionBar()?.hide();

        val horizontalLayoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        recyclerview.layoutManager = horizontalLayoutManager

        presenter.loadCurrencies(baseCurrency)

        CurrencyValue.addTextChangedListener(object : TextWatcher {

                override fun afterTextChanged(s: Editable) {}
                override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(
                    s: CharSequence, start: Int,
                    before: Int, count: Int
                ) {
                    presenter.refresh(spinner.selectedItem.toString())
                }
            })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.refresh(spinner.selectedItem.toString())
            }
        }
        val imgResId =("flag_" + (spinner.selectedItem).toString().toLowerCase())
        val resId = resources.getIdentifier(imgResId , "drawable", packageName)
        currentFlag.setImageResource(resId)
    }

    override fun showProgress() {}

    override fun showError(e: Throwable) {}

    override fun clearData() {}

    override fun showCurrencies(viewData: CurrencyResponse) {
        updateItemAdapterData(viewData)
        refreshActivity.isRefreshing = false
    }



    private var baseCurrency = "RUB"

    @InjectPresenter
    lateinit var presenter:CurrenciesPresenter
    lateinit var recyclerAdapter:RecyclerAdapter


    @Inject
    lateinit var interactor : CurrenciesInteractor

    @ProvidePresenter
    fun providePresenter():CurrenciesPresenter{
        return CurrenciesPresenter(interactor)
    }

    private fun updateItemAdapterData(d:CurrencyResponse){
            recyclerAdapter = RecyclerAdapter(d, BigDecimal.valueOf((CurrencyValue.text.toString()).toDouble()),this)
            recyclerview.adapter = recyclerAdapter

            recyclerAdapter.refreshData(d)
            recyclerAdapter.notifyDataSetChanged()

    }
}