package code.christian80gabi

import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import io.ktor.utils.io.*
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        println("Hello World!")

        val selectorManager = SelectorManager(Dispatchers.IO)
        val serverSocket = aSocket(selectorManager).tcp().bind("127.0.0.1", 9002)
        println("Server listening on ${serverSocket.localAddress}")
        while (true) {
            val socket = serverSocket.accept()
            println("Accept $socket")
            launch {
                val receiveChannel = socket.openReadChannel()
                val sendChannel = socket.openWriteChannel(autoFlush = true)
                sendChannel.writeStringUtf8("Please enter your name\n")
                try {
                    while (true) {
                        val name = receiveChannel.readUTF8Line()
                        sendChannel.writeStringUtf8("Hello $name!\n")
                    }
                } catch (e: Throwable) {
                    socket.close()
                }
            }
        }
    }
}