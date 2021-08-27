package com.excercise.bareksatest.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.excercise.bareksatest.R
import com.excercise.bareksatest.core.adapter.ProductButtonAdapter
import com.excercise.bareksatest.core.adapter.ProductSubImageAdapter
import com.excercise.bareksatest.core.adapter.ProductSubTextAdapter
import com.excercise.bareksatest.core.data.Resource
import com.excercise.bareksatest.core.domain.model.*
import com.excercise.bareksatest.databinding.FragmentMainBinding
import com.excercise.bareksatest.ui.custom.CustomHighlightChart
import com.excercise.bareksatest.utils.CustomFunctions
import com.excercise.bareksatest.utils.FilterChart
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

    private var dataProductOne = ArrayList<MChartDataProduct>()
    private var dataProductTwo = ArrayList<MChartDataProduct>()
    private var dataProductThree = ArrayList<MChartDataProduct>()
    private val listPercentageFormatter = ArrayList<String>()

    private lateinit var productAdapter: ProductSubImageAdapter
    private lateinit var menuAdapter: ProductSubTextAdapter
    private lateinit var buttonAdapter: ProductButtonAdapter
    private val listProductBareksa = ArrayList<CustomBannerModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        //Mobile
        setupMobileRv()
        observeChartData()
        observeProductsData()
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

    private fun observeProductsData() = CoroutineScope(Dispatchers.Main).launch {
        viewModel.getMultipleDetailProducts(
            "KI002MMCDANCAS00",
            "TP002EQCEQTCRS00",
            "NI002EQCDANSIE00"
        ).observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {
                    Log.d("DATA->", response.data.toString())
                    response.data?.let { populateDataDetail(it) }
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
        populateDataChart(null)
    }

    private fun populateDataDetail(listDetailProduct: List<MDetailProduct>) {
        val tempListSection = ArrayList<MenuDataModel>()
        val tempListBanner = ArrayList<BannerModel>()

        // ListDataMenu
        val listTypeString = ArrayList<String>()
        val listFiveYear = ArrayList<String>()
        val listMoneyCurrent = ArrayList<String>()
        val listMinBuy = ArrayList<String>()
        val listTermTime = ArrayList<String>()
        val listRisk = ArrayList<String>()
        val listLaunching = ArrayList<String>()

        listDetailProduct.forEachIndexed { index, product ->
            tempListBanner.add(BannerModel(index, product.im_avatar, product.name))
            listTypeString.add(product.type)
            listFiveYear.add("${product.return_five_year}%/5thn")
            listMoneyCurrent.add(CustomFunctions.parseValueIntoMoneyExtension(product.nav * product.total_unit))
            listMinBuy.add(CustomFunctions.parseValueIntoMoneyExtension(product.min_subscription.toDouble()))
            listTermTime.add("5 Tahun")
            listRisk.add("Tinggi")
            listLaunching.add(CustomFunctions.parseDateTimeToDateTimeRead(product.inception_date))
        }
        tempListSection.add(MenuDataModel("Jenis reksa dana", listTypeString))
        tempListSection.add(MenuDataModel("Imbal Hasil", listFiveYear))
        tempListSection.add(MenuDataModel("Dana Kelolaan", listMoneyCurrent))
        tempListSection.add(MenuDataModel("Min Pembelian", listMinBuy))
        tempListSection.add(MenuDataModel("Jangka Waktu", listTermTime))
        tempListSection.add(MenuDataModel("Tingkat Resiko", listRisk))
        tempListSection.add(MenuDataModel("Peluncuran", listLaunching))

        listProductBareksa.add(
            CustomBannerModel(
                tempListBanner,
                tempListSection
            )
        )
        productAdapter.differ.submitList(tempListBanner.toMutableList())
        menuAdapter.differ.submitList(tempListSection.toMutableList())
        buttonAdapter.differ.submitList(listTypeString.toMutableList())

    }

    private fun setupMobileRv() {
        productAdapter = ProductSubImageAdapter()
        menuAdapter = ProductSubTextAdapter()
        buttonAdapter = ProductButtonAdapter()
        binding.rvItemBanner.apply {
            layoutManager = GridLayoutManager(binding.root.context, 3)
            adapter = productAdapter
        }
        binding.rvItemData.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = menuAdapter
        }
        binding.rvItemButton.apply {
            layoutManager = GridLayoutManager(binding.root.context, 3)
            adapter = buttonAdapter
        }
    }

    private fun showChart() {
        // chart setup
        val markerView =
            CustomHighlightChart(binding.root.context, R.layout.custom_highlight_one)
        binding.chartProducts.marker = markerView
        binding.chartProducts.setTouchEnabled(true)

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

        binding.chartProducts.invalidate()
        setupTabLayoutChartFilter()
        binding.lnrDataOnShow.visibility = View.VISIBLE
    }

    private fun setupDataSetChart() {
        dataSetOne =
            dataProductOne.mapIndexedTo(ArrayList()) { i, model ->
                listDate.add(CustomFunctions.parseDateTimeToMonthDay(model.date))
                Entry(
                    i.toFloat(),
                    model.growth.toFloat()
                )
            }
        Log.d("LASTPRODUCT->", dataProductOne.last().toString())
        Log.d("LASTDATASET->", dataSetOne.last().toString())
        dataSetTwo =
            dataProductTwo.mapIndexedTo(ArrayList()) { i, model ->
                Entry(
                    i.toFloat(),
                    model.growth.toFloat()
                )
            }
        dataSetThree =
            dataProductThree.mapIndexedTo(ArrayList()) { i, model ->
                Entry(
                    i.toFloat(),
                    model.growth.toFloat()
                )
            }

        val sortedDataMuchLargeValue = arrayListOf(
            dataProductOne.maxByOrNull { it.growth }!!,
            dataProductTwo.maxByOrNull { it.growth }!!,
            dataProductThree.maxByOrNull { it.growth }!!
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

        val lineDataChart = LineData(lineDataSetOne, lineDataSetTwo, lineDataSetThree)
        binding.chartProducts.data = lineDataChart
        binding.chartProducts.setOnChartValueSelectedListener(object :
            OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry?, h: Highlight?) {
                binding.txtValueChartOne.text = (e?.x?.toInt()?.let {
                    dataProductOne[it].growth.toString() + "%"
                })

                binding.txtValueChartTwo.text = (e?.x?.toInt()?.let {
                    dataProductTwo[it].growth.toString() + "%"
                })

                binding.txtValueChartThree.text = (e?.x?.toInt()?.let {
                    dataProductThree[it].growth.toString() + "%"
                })
            }

            override fun onNothingSelected() {
            }
        })
    }

    private fun setupTabLayoutChartFilter() {
        binding.tlFilterChart.visibility = View.VISIBLE
        binding.tlFilterChart.getTabAt(6)?.select()
        binding.tlFilterChart.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        populateDataChart(FilterChart.ONEWEEK)
                    }
                    1 -> {
                        populateDataChart(FilterChart.ONEMONTH)
                    }
                    2 -> {
                        populateDataChart(FilterChart.ONEYEAR)
                    }
                    3 -> {
                        populateDataChart(FilterChart.THREEYEAR)
                    }
                    4 -> {
                        populateDataChart(FilterChart.FIFTHYEAR)
                    }
                    5 -> {
                        populateDataChart(FilterChart.TENTHYEAR)
                    }
                    6 -> {
                        populateDataChart(null)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun populateDataChart(typeFilter: FilterChart?) {
        when (typeFilter) {
            FilterChart.ONEWEEK -> {
                filterData(FilterChart.ONEWEEK)
            }
            FilterChart.ONEMONTH -> {
                filterData(FilterChart.ONEMONTH)
            }
            FilterChart.ONEYEAR -> {
                filterData(FilterChart.ONEYEAR)
            }
            FilterChart.THREEYEAR -> {
                filterData(FilterChart.THREEYEAR)
            }
            FilterChart.FIFTHYEAR -> {
                filterData(FilterChart.FIFTHYEAR)
            }
            FilterChart.TENTHYEAR -> {
                filterData(FilterChart.TENTHYEAR)
            }
            else -> {
                listPercentageFormatter.clear()
                dataProductOne.clear()
                dataProductTwo.clear()
                dataProductThree.clear()

                productOneChart?.listDataChartProduct?.let { dataProductOne.addAll(it) }
                productTwoChart?.listDataChartProduct?.let { dataProductTwo.addAll(it) }
                productThreeChart?.listDataChartProduct?.let { dataProductThree.addAll(it) }
                setupDataSetChart()
                showChart()
            }
        }
    }

    private fun filterData(filterType: FilterChart) {
        listPercentageFormatter.clear()
        dataProductOne.clear()
        dataProductTwo.clear()
        dataProductThree.clear()

        val firstIndex = 0
        val tempOne = productOneChart?.listDataChartProduct!!.reversed()
        val tempTwo = productTwoChart?.listDataChartProduct!!.reversed()
        val tempThree = productThreeChart?.listDataChartProduct!!.reversed()

        if (tempOne.size > filterType.countByDays) {
            tempOne.subList(firstIndex, filterType.countByDays).let {
                Log.d("ITSIZE->", it.size.toString())
                dataProductOne.addAll(it.reversed())
                Log.d("DATAPRODUCTONE->", dataProductOne.size.toString())
            }

            tempTwo.subList(firstIndex, filterType.countByDays).let {
                dataProductTwo.addAll(it.reversed())
            }

            tempThree.subList(firstIndex, filterType.countByDays).let {
                dataProductThree.addAll(it.reversed())
            }

            setupDataSetChart()
            binding.chartProducts.invalidate()
        } else {
            Toast.makeText(
                binding.root.context,
                "Maaf, data tidak tersedia",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}