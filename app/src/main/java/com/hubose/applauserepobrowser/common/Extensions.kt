package com.hubose.applauserepobrowser.common

import android.text.*
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView

fun View.setVisible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun TextView.addTextStyled(string: SpannableString) {
    val spannableString = SpannableString(this.text)
    this.text = TextUtils.concat(spannableString, " ", string)
}

fun TextView.url(url: String, text: String) {
    val string = "<a href=\"$url\">$text</a>"
    this.text = Html.fromHtml(string)
    this.movementMethod = LinkMovementMethod.getInstance()
}

fun EditText.onTextChanged(onTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object: TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            onTextChanged.invoke(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    })
}