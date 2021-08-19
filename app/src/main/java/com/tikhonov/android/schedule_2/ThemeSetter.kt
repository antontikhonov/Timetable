package com.tikhonov.android.schedule_2;

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat

private const val DRAWABLE = "drawable"

class ThemeSetter {
    companion object {
        fun setImage(
            context: Context,
            packageName: String,
            nameImage: String,
            imageView: ImageView
        ) {
            val drawableId = context.resources.getIdentifier(nameImage, DRAWABLE, packageName)
            imageView.setImageResource(drawableId)
        }

        fun setButtonsTextColor(buttons: List<Button>, colorCode: String) {
            buttons.forEach {
                it.setTextColor(Color.parseColor(colorCode))
            }
        }

        fun setButtonsColor(
            context: Context,
            packageName: String,
            path: String,
            buttons: List<Button>
        ) {
            val drawableId = context.resources.getIdentifier(path, DRAWABLE, packageName)
            val shapeDrawable = ContextCompat.getDrawable(context, drawableId)
            buttons.forEach {
                it.background = shapeDrawable
            }
        }

        fun setBackgroundViews(list: List<View>, colorCode: String) {
            list.forEach {
                it.setBackgroundColor(Color.parseColor(colorCode))
            }

        }
    }
}