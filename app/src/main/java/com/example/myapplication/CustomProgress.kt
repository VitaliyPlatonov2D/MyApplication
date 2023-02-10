package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomProgress (context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {


    private val aPaint: Paint = Paint()
    private val bPaint: Paint = Paint()
    private val cPaint: Paint = Paint()
//сколько процентов числа
    var numOne = 2000f
    var numTwo = 1120f
    var numThree = 2340f
    var numSum = numOne + numTwo + numThree

    var numSize = 65
// расчкты на диограмее
    fun Calculation(indexOne: Float, sumNum: Float):Float{
        return (indexOne/sumNum) * 360f
    }
    fun CalculationTextProcent (num: Float) : Float {
        return (num/360) * 100
    }


    private  var startAngleAOval = 0f
    private var sweepAngleAOval = Calculation(numOne,numSum)
    private var startAngleBOval = sweepAngleAOval
    private var sweepAngleBOval = Calculation(numTwo,numSum)
    private var startAngleCOval = sweepAngleBOval + sweepAngleAOval
    private var sweepAngleCOval = Calculation(numThree,numSum)

    var oneNum = CalculationTextProcent(sweepAngleAOval).toInt()
    var twoNum = CalculationTextProcent(sweepAngleBOval).toInt()
    var threeNum = CalculationTextProcent(sweepAngleCOval).toInt()// для процентов

    constructor(context: Context?) : this(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {

        aPaint.textSize = numSize.toFloat()
        bPaint.textSize = numSize.toFloat()
        cPaint.textSize = numSize.toFloat()

        aPaint.color = Color.parseColor("#C24346")
        bPaint.color = Color.parseColor("#C37C43")
        cPaint.color = Color.parseColor("#308D89")
        val x = ((width - height / 2) / 2).toFloat()
        val y = (height / 4).toFloat()
        val oval = RectF(x+50, y, width - x -50, height - y-100)//круг рисунок
        canvas.drawArc(oval,startAngleAOval, sweepAngleAOval  , true, aPaint)
        canvas.drawArc(oval,startAngleBOval, sweepAngleBOval, true, bPaint)
        canvas.drawArc(oval, startAngleCOval, sweepAngleCOval, true, cPaint)//разделения на цвета
        canvas.drawText(twoNum.toString() + "%", 20f, 1800f, bPaint)
        canvas.drawText(oneNum.toString() + "%", 940f,1800f, aPaint)
        canvas.drawText(threeNum.toString() + "%", 500f, 1800f, cPaint)
    }

}