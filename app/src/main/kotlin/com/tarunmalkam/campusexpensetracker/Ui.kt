package com.tarunmalkam.campusexpensetracker

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

object Ui {
    const val GREEN = Color.rgb(46, 125, 50)
    const val LIGHT_GREEN = Color.rgb(232, 245, 233)
    const val DARK_TEXT = Color.rgb(32, 32, 32)
    const val MUTED_TEXT = Color.rgb(94, 94, 94)
    const val DANGER = Color.rgb(198, 40, 40)
    const val WARNING = Color.rgb(245, 124, 0)

    fun screen(activity: Activity): LinearLayout {
        val scrollView = ScrollView(activity)
        scrollView.setBackgroundColor(Color.WHITE)

        val container = LinearLayout(activity)
        container.orientation = LinearLayout.VERTICAL
        container.setPadding(28, 28, 28, 28)
        scrollView.addView(container)

        activity.setContentView(scrollView)
        return container
    }

    fun title(activity: Activity, text: String): TextView {
        return TextView(activity).apply {
            this.text = text
            textSize = 26f
            setTextColor(DARK_TEXT)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 8, 0, 8)
        }
    }

    fun subtitle(activity: Activity, text: String): TextView {
        return TextView(activity).apply {
            this.text = text
            textSize = 15f
            setTextColor(MUTED_TEXT)
            setPadding(0, 0, 0, 18)
        }
    }

    fun sectionTitle(activity: Activity, text: String): TextView {
        return TextView(activity).apply {
            this.text = text
            textSize = 19f
            setTextColor(DARK_TEXT)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 22, 0, 10)
        }
    }

    fun body(activity: Activity, text: String): TextView {
        return TextView(activity).apply {
            this.text = text
            textSize = 15f
            setTextColor(DARK_TEXT)
            setPadding(0, 6, 0, 6)
        }
    }

    fun muted(activity: Activity, text: String): TextView {
        return TextView(activity).apply {
            this.text = text
            textSize = 14f
            setTextColor(MUTED_TEXT)
            setPadding(0, 3, 0, 3)
        }
    }

    fun button(activity: Activity, text: String, onClick: () -> Unit): Button {
        return Button(activity).apply {
            this.text = text
            setTextColor(Color.WHITE)
            setBackgroundColor(GREEN)
            setAllCaps(false)
            textSize = 15f
            setPadding(10, 10, 10, 10)
            setOnClickListener { onClick() }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        }
    }

    fun outlineButton(activity: Activity, text: String, onClick: () -> Unit): Button {
        return Button(activity).apply {
            this.text = text
            setTextColor(GREEN)
            setBackgroundColor(LIGHT_GREEN)
            setAllCaps(false)
            textSize = 15f
            setPadding(10, 10, 10, 10)
            setOnClickListener { onClick() }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        }
    }

    fun dangerButton(activity: Activity, text: String, onClick: () -> Unit): Button {
        return Button(activity).apply {
            this.text = text
            setTextColor(Color.WHITE)
            setBackgroundColor(DANGER)
            setAllCaps(false)
            textSize = 14f
            setPadding(10, 8, 10, 8)
            setOnClickListener { onClick() }
        }
    }

    fun input(activity: Activity, hint: String): EditText {
        return EditText(activity).apply {
            this.hint = hint
            textSize = 15f
            setSingleLine(false)
            setPadding(12, 8, 12, 8)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        }
    }

    fun card(activity: Activity): LinearLayout {
        return LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(22, 18, 22, 18)
            setBackgroundColor(LIGHT_GREEN)
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 12)
            }
        }
    }

    fun row(activity: Activity): LinearLayout {
        return LinearLayout(activity).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    fun divider(activity: Activity): View {
        return View(activity).apply {
            setBackgroundColor(Color.rgb(230, 230, 230))
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
            ).apply {
                setMargins(0, 16, 0, 16)
            }
        }
    }

    fun toast(activity: Activity, message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}
