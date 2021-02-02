package com.shopping.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.w3c.files.File as JSFile

internal actual val IODispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

actual typealias File = JSFile

actual fun File.toByteArray(): ByteArray {
    TODO()
}
