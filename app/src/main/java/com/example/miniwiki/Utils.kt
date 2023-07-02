package com.example.miniwiki

import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import java.text.NumberFormat
import java.util.Locale

fun mapToString(languages: Map<String, String>):String{
    var result = ""
    val len = languages.size
    var counter = 0
    for (language in languages) {
        counter += 1
        if (counter == len) {
            result += language.value
        } else {
            result += "${language.value}, "
        }
    }
    return result
}

fun formatNumber(number: Long):String {
    return NumberFormat.getInstance().format(number)
}

suspend fun loadSvg(imageView: ImageView, flags: Flags) {
    val url: String = flags.svg
    if (url.lowercase(Locale.ENGLISH).endsWith("svg")){
        val imageLoader = ImageLoader.Builder(imageView.context)
            .components(){
                add(SvgDecoder.Factory())
            }
            .build()
        val request = ImageRequest.Builder(imageView.context)
            .data(url)
            .target(imageView)
            .build()
        imageLoader.execute(request)
    } else {
        imageView.load(url)
    }
}
