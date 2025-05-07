package org.beijingteam.presentation.consoleIO

import org.beijingteam.presentation.consoleIO.ConsoleIO

class ConsoleIOImpl : ConsoleIO {
    override fun show(message: String) {
        print(message)
    }

    override fun showWithLine(message: String) {
        println(message)
    }

    override fun read(): String? {
        return readlnOrNull()?.trim()
    }

}