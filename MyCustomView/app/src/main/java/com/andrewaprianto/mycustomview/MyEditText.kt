package com.andrewaprianto.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.res.ResourcesCompat

class MyEditText : AppCompatEditText, View.OnTouchListener {

    lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukkan Nama Anda!"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        mClearButtonImage =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_close_black_24dp, null) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do Nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }

            override fun afterTextChanged(p0: Editable?) {
                //Do Nothing
            }
        })
    }

    private fun showClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

    private fun hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false

            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    when {
                        event.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }

            when {
                isClearButtonClicked ->
                    when {
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_close_black_24dp,
                                null
                            ) as Drawable
                            showClearButton()
                            return true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ResourcesCompat.getDrawable(
                                resources,
                                R.drawable.ic_close_black_24dp,
                                null
                            ) as Drawable
                            when {
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return true
                        }
                        else -> return false
                    }
                else -> return false
            }
        }
        return false
    }

}