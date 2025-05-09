package com.rondi.ronmovies.util

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Parcelable
import android.view.MotionEvent
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.rondi.ronmovies.R
import com.rondi.ronmovies.domain.model.Genre
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.roundToInt

fun Fragment.playYouTubeVideo(videoKey: String) {
    val webIntent =
        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=$videoKey"))
    startActivity(webIntent)
}

fun ChipGroup.setGenreChips(
    genres: List<Genre>,
    detailType: Detail,
    backgroundColor: Int
) {
    genres.forEach { genre ->
        addView(
            Chip(context).apply {
                setChipBackgroundColorResource(if (backgroundColor.isDarkColor()) R.color.white else R.color.black)
                text = genre.name
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                setTextColor(backgroundColor.setTintColor(true))

                setOnClickListener {
                    val bundle = bundleOf(
                        Constants.CONTENT_TYPE to Content.GENRE as Parcelable,
                        Constants.DETAIL_TYPE to detailType as Parcelable,
                        Constants.GENRE_ID to genre.id,
                        Constants.TITLE to genre.name,
                        Constants.BACKGROUND_COLOR to backgroundColor
                    )

                    findNavController().navigate(R.id.action_global_seeAllFragment, bundle)
                }
            }
        )
    }
}

fun RecyclerView.interceptTouch() {
    addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            return canScrollHorizontally(RecyclerView.FOCUS_FORWARD).let {
                rv.parent.requestDisallowInterceptTouchEvent(it && e.action == MotionEvent.ACTION_MOVE)
                if (!it) removeOnItemTouchListener(this)
                !it
            }
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
    })
}

fun Int.isDarkColor(): Boolean {
    val darkness =
        1 - (0.299 * Color.red(this) + 0.587 * Color.green(this) + 0.114 * Color.blue(this)) / 255
    return darkness >= 0.5
}

fun Int.setTintColor(reverse: Boolean = false): Int =
    if (this.isDarkColor() xor reverse) Color.WHITE else Color.BLACK

fun Int?.formatTime(context: Context): String? = this?.let {
    when {
        it == 0 -> return null
        it >= 60 -> {
            val hours = it / 60
            val minutes = it % 60
            "${hours}${context.getString(R.string.hour_short)} ${
                if (minutes == 0) "" else "$minutes${
                    context.getString(
                        R.string.minute_short
                    )
                }"
            }"
        }

        else -> "${it}${context.getString(R.string.minute_short)}"
    }
}

fun Long.thousandsSeparator(context: Context): String = with(DecimalFormatSymbols()) {
    groupingSeparator = context.getString(R.string.thousand_separator).toCharArray()[0]
    DecimalFormat("#,###", this).format(this@thousandsSeparator)
}

fun Double.round(): Double = (this * 10.0).roundToInt() / 10.0

fun Double.asPercent(): String = "%${(this * 10).toInt()}"

fun String?.formatDate(): String {
    val outputFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.US)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    return if (!this.isNullOrEmpty()) outputFormat.format(inputFormat.parse(this)) else ""
}