package org.beijingteam.presentation.consoleIO

interface ConsoleIO {
    fun show(message: String)
    fun showWithLine(message: String)
    fun read(): String?
}