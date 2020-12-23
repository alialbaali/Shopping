package com.shopping.frontend

import kotlinx.browser.window
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLInputElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget
import react.*

// Function that takes RBuilder to build a component just like a functionalComponent and returns an extension function on RBuilder
// which takes another props extension function

fun <P : RProps> emptyProps(): Handler<P> = {}

typealias Handler<P> = P.() -> Unit

fun <P : RProps> fc(displayName: String? = null, func: RBuilder.(P) -> Unit): RBuilder.(Handler<P>) -> ReactElement = { handler ->
    child(functionalComponent(displayName, func)) {
        attrs(handler)
    }
}

inline fun <reified T : HTMLElement> EventTarget?.asHtmlElement() = this as T

fun isSystemInDarkMode() = window.matchMedia("(prefers-color-scheme: dark)").matches

val Event.inputValue
    get() = target.asHtmlElement<HTMLInputElement>().value
