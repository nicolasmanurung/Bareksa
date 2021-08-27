package com.excercise.bareksatest.ui.custom

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class CustomHighlightChart(context: Context?, layoutResource: Int) :
    MarkerView(context, layoutResource) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-(width / 1.2)).toFloat(), (-(height / 1.2)).toFloat())
    }
}