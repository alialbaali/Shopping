package com.shopping.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import java.io.File as JFile

internal actual val IODispatcher: CoroutineDispatcher
    get() = Dispatchers.IO

actual typealias File = JFile

actual fun File.toByteArray() = readBytes()