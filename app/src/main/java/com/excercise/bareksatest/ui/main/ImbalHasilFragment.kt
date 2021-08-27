package com.excercise.bareksatest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.excercise.bareksatest.R
import com.excercise.bareksatest.core.data.Resource
import com.excercise.bareksatest.core.domain.model.MChart
import com.excercise.bareksatest.databinding.FragmentMainBinding
import com.excercise.bareksatest.ui.custom.CustomHighlightChart
import com.excercise.bareksatest.utils.CustomFunctions
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImbalHasilFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private val binding get() = _binding!!
    private var productOneChart: MChart? = null
    private var productTwoChart: MChart? = null
    private var productThreeChart: MChart? = null
    private val listDate = ArrayList<String>()
    private var dataSetOne = ArrayList<Entry>()
    private var dataSetTwo = ArrayList<Entry>()
    private var dataSetThree = ArrayList<Entry>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        observeChartData()

        return binding.root
    }

    companion object {
        private const val TAG = "ImbalHasilFragment"
    }

    private fun observeChartData() = CoroutineScope(Dispatchers.Main).launch {
        viewModel.getMultipleChartProducts(
            "KI002MMCDANCAS00",
            "TP002EQCEQTCRS00",
            "NI002EQCDANSIE00"
        ).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d("DATA->", response.data.toString())
                    response.data?.let { populateChartData(it) }
                }
                is Resource.Error -> {
                    Log.d("ERROR->", response.message.toString())
                }
                is Resource.Loading -> {

                }
            }
        })
    }

    private fun populateChartData(listData: List<MChart>) {
        listData.forEach { chartData ->
            when (chartData.nameProduct) {
                "KI002MMCDANCAS00" -> {
                    productOneChart = chartData
                }

                "TP002EQCEQTCRS00" -> {
                    productTwoChart = chartData
                }

                "NI002EQCDANSIE00" -> {
                    productThreeChart = chartData
                }
            }
        }

        setupDataSetChart()
        showDataChart()
    }


    private fun showDataChart() {
        val listPercentageFormatter = ArrayList<String>()

        val sortedDataMuchLargeValue = arrayListOf(
            productOneChart?.listDataChartProduct?.maxByOrNull { it.growth }!!,
            productTwoChart?.listDataChartProduct?.maxByOrNull { it.growth }!!,
            productThreeChart?.listDataChartProduct?.maxByOrNull { it.growth }!!
        ).sortedByDescending { it.growth }

        val secondIndex = 0.2.times(sortedDataMuchLargeValue.first().growth.toInt())
        val thirdIndex = 0.4.times(sortedDataMuchLargeValue.first().growth.toInt())
        val fourthIndex = 0.5.times(sortedDataMuchLargeValue.first().growth.toInt())
        val fifthIndex = 0.7.times(sortedDataMuchLargeValue.first().growth.toInt())
        val sixthIndex = 0.9.times(sortedDataMuchLargeValue.first().growth.toInt())
        val lastIndex = sortedDataMuchLargeValue.first().growth.toInt()


        listPercentageFormatter.add("0")
        listPercentageFormatter.add("$secondIndex%")
        listPercentageFormatter.add("$thirdIndex%")
        listPercentageFormatter.add("$fourthIndex%")
        listPercentageFormatter.add("$fifthIndex%")
        listPercentageFormatter.add("$sixthIndex%")
        listPercentageFormatter.add("$lastIndex%")

        // chart data one
        val lineDataSetOne = LineDataSet(dataSetOne, "")
        lineDataSetOne.color = ContextCompat.getColor(binding.root.context, R.color.chartOneColor)
        lineDataSetOne.lineWidth = 2f
        lineDataSetOne.setDrawCircles(false)
        lineDataSetOne.setDrawValues(false)
        lineDataSetOne.mode = LineDataSet.Mode.CUBIC_BEZIER

        // chart data two
        val lineDataSetTwo = LineDataSet(dataSetTwo, "")
        lineDataSetTwo.color = ContextCompat.getColor(binding.root.context, R.color.chartTwoColor)
        lineDataSetTwo.lineWidth = 2f
        lineDataSetTwo.setDrawCircles(false)
        lineDataSetTwo.setDrawValues(false)
        lineDataSetTwo.mode = LineDataSet.Mode.CUBIC_BEZIER

        // chart data three
        val lineDataSetThree = LineDataSet(dataSetThree, "")
        lineDataSetThree.color =
            ContextCompat.getColor(binding.root.context, R.color.chartThreeColor)
        lineDataSetThree.lineWidth = 2f
        lineDataSetThree.setDrawCircles(false)
        lineDataSetThree.setDrawValues(false)
        lineDataSetThree.mode = LineDataSet.Mode.CUBIC_BEZIER

        // chart setup
        val markerView =
            CustomHighlightChart(binding.root.context, R.layout.custom_highlight_one)
        val lineDataChart = LineData(lineDataSetOne, lineDataSetTwo, lineDataSetThree)

        binding.chartProducts.marker = markerView
        binding.chartProducts.setTouchEnabled(true)
        binding.chartProducts.data = lineDataChart
        binding.chartProducts.xAxis.setDrawGridLines(false)
        binding.chartProducts.xAxis.valueFormatter = IndexAxisValueFormatter(listDate)
        binding.chartProducts.xAxis.textColor =
            ContextCompat.getColor(binding.root.context, R.color.shadowColor)
        binding.chartProducts.xAxis.textSize = 10f

        binding.chartProducts.axisRight.valueFormatter =
            IndexAxisValueFormatter(listPercentageFormatter)
        binding.chartProducts.axisRight.textColor =
            ContextCompat.getColor(binding.root.context, R.color.shadowColor)
        binding.chartProducts.axisRight.textSize = 10f
        binding.chartProducts.axisRight.axisMinimum = 0f
        binding.chartProducts.axisRight.axisMaximum = 7f
        binding.chartProducts.axisRight.setDrawAxisLine(false)
        binding.chartProducts.extraRightOffset = 20f

        binding.chartProducts.axisLeft.isEnabled = false
        binding.chartProducts.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.chartProducts.setScaleEnabled(false)
        binding.chartProducts.description.text = ""
        binding.chartProducts.setDrawBorders(false)
        binding.chartProducts.setBorderWidth(0f)
        binding.chartProducts.legend.isEnabled = false
        binding.chartProducts.animateXY(1000, 1000)

        binding.chartProducts.setOnChartValueSelectedListener(object :
            OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                binding.txtValueChartOne.text = (e?.x?.toInt()?.let {
                    productOneChart?.listDataChartProduct?.get(
                        it
                    )?.growth.toString() + "%"
                })

                binding.txtValueChartTwo.text = (e?.x?.toInt()?.let {
                    productTwoChart?.listDataChartProduct?.get(
                        it
                    )?.growth.toString() + "%"
                })

                binding.txtValueChartThree.text = (e?.x?.toInt()?.let {
                    productThreeChart?.listDataChartProduct?.get(
                        it
                    )?.growth.toString() + "%"
                })
            }

            override fun onNothingSelected() {
            }

        })

        binding.chartProducts.invalidate()
        setupTabLayoutChartFilter()
        binding.lnrDataOnShow.visibility = View.VISIBLE
    }

    private fun setupDataSetChart() {
        if (productOneChart?.listDataChartProduct != null) {
            dataSetOne =
                productOneChart?.listDataChartProduct?.mapIndexedTo(ArrayList()) { i, model ->
                    listDate.add(CustomFunctions.parseDateTimeToMonthDay(model.date))
                    Entry(
                        i.toFloat(),
                        model.growth.toFloat()
                    )
                }!!
        }
        if (productTwoChart?.listDataChartProduct != null) {
            dataSetTwo =
                productTwoChart?.listDataChartProduct?.mapIndexedTo(ArrayList()) { i, model ->
                    Entry(
                        i.toFloat(),
                        model.growth.toFloat()
                    )
                }!!
        }
        if (productThreeChart?.listDataChartProduct != null) {
            dataSetThree =
                productThreeChart?.listDataChartProduct?.mapIndexedTo(ArrayList()) { i, model ->
                    Entry(
                        i.toFloat(),
                        model.growth.toFloat()
                    )
                }!!
        }
    }

    private fun setupTabLayoutChartFilter() {
        binding.tlFilterChart.visibility = View.VISIBLE
        binding.tlFilterChart.getTabAt(6)?.select()
        binding.tlFilterChart.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    4 -> {

                    }
                    5 -> {

                    }
                    6 -> {

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}