package com.ultralogs.iyan.samplecalculator

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //variable helpers
    var firstClick = true
    var op = "*"
    var oldVale = ""
    var isNewOp = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Dealing with soft keyboard
        //if SDK API version is greater 11 r less than 21
        if (Build.VERSION.SDK_INT >= 21) {
            dashbaord!!.showSoftInputOnFocus = false
        } else if (Build.VERSION.SDK_INT >= 11) {
            dashbaord!!.setRawInputType(InputType.TYPE_CLASS_TEXT)
            dashbaord!!.setTextIsSelectable(true)
        } else {
            dashbaord!!.setRawInputType(InputType.TYPE_NULL)
            dashbaord!!.isFocusable = true
        }
    }

    /**
     * press numbers
     */
    fun numClick(view: View) {

        val buSelect = view as Button
        var valueTExt: String = dashbaord.text.toString()
        if (firstClick) {
            valueTExt = ""
            firstClick = false
        }
        if (isNewOp) {
            //Toast.makeText(this, "working", Toast.LENGTH_LONG).show()
            valueTExt = ""
        }
        isNewOp = false
        when (buSelect.id) {
            num0.id -> {
                valueTExt += "0"
            }
            num1.id -> {
                valueTExt += "1"
            }
            num2.id -> {
                valueTExt += "2"
            }
            num3.id -> {
                valueTExt += "3"
            }
            num4.id -> {
                valueTExt += "4"
            }
            num5.id -> {
                valueTExt += "5"
            }
            num6.id -> {
                valueTExt += "6"
            }
            num7.id -> {
                valueTExt += "7"
            }
            num8.id -> {
                valueTExt += "8"
            }
            num9.id -> {
                valueTExt += "9"
            }
            numdot.id -> {
                if (!valueTExt.contains(".")) {
                    valueTExt += "."
                }
            }
            plusminus.id -> {
                if (valueTExt.length > 0)
                    valueTExt = (-1 * valueTExt.toFloat()).toString()
            }
            else -> {

            }
        }
        dashbaord.setText(valueTExt.toString())

    }

    /**
     * operation
     */
    fun opEvent(view: View) {
        val buSelect = view as Button
        when (buSelect.id) {
            div.id -> {
                op = "/"
            }
            mul.id -> {
                op = "*"
            }
            sub.id -> {
                op = "-"
            }
            sum.id -> {
                op = "+"
            }
        }
        oldVale = dashbaord.text.toString()
        isNewOp = true
    }

    /**
     * operation results
     */
    fun opEqual(view: View) {
        val newValue = dashbaord.text.toString()
        var finalValue: Float? = null
        when (op) {
            "*" -> {
                finalValue = oldVale.toFloat() * newValue.toFloat()
            }
            "/" -> {
                finalValue = oldVale.toFloat() / newValue.toFloat()
            }
            "-" -> {
                finalValue = oldVale.toFloat() - newValue.toFloat()
            }
            "+" -> {
                finalValue = oldVale.toFloat() + newValue.toFloat()
            }
        }
        dashbaord.setText(finalValue.toString())
        isNewOp = true
    }

    /**
     * for clean desplay button
     */
    fun acOp(view: View) {
        dashbaord.setText("0")
        isNewOp = true
    }

    /**
     * for percentage button
     */
    fun perOp(view: View) {

        val num: Float = dashbaord.text.toString().toFloat() / 100
        dashbaord.setText(num.toString())
    }
}
