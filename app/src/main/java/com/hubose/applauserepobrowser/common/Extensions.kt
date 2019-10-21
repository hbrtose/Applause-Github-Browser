package com.hubose.applauserepobrowser.common

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect
import java.util.concurrent.TimeUnit

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

@ExperimentalCoroutinesApi
fun <T> ReceiveChannel<T>.debounce(time: Long, scope: CoroutineScope): ReceiveChannel<T> =
    Channel<T>(capacity = Channel.CONFLATED).also { channel ->
        scope.launch {
            var value = receive()
            whileSelect {
                onTimeout(time) {
                    channel.offer(value)
                    value = receive()
                    true
                }
                onReceive {
                    value = it
                    true
                }
            }
        }
    }

fun EditText.onTextChanged(): ReceiveChannel<String> =
    Channel<String>(capacity = Channel.UNLIMITED).also { channel ->
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                editable?.toString().orEmpty().let(channel::offer)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}