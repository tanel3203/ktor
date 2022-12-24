package ee.entusiast

import ee.entusiast.models.populate
import ee.entusiast.models.resultStorage
import io.ktor.server.application.*
import ee.entusiast.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureRouting()
    MyApp().apply { main() }
}

class MyApp {
    fun main() {
        println("Started")
        populate()

        println("Initial data: ")
        resultStorage.forEach { println(it) }
    }
}