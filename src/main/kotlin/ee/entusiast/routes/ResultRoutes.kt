package ee.entusiast.routes

import ee.entusiast.models.Result
import ee.entusiast.models.resultStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.resultRouting() {
    route("result") {
        get {
            log("get")
            if (resultStorage.isNotEmpty()) {
                call.respond(resultStorage)
            } else {
                call.respondText("No res found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            log("get by id")
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val res =
                resultStorage.find { it.id == id.toInt() } ?: return@get call.respondText(
                    "No res with id $id",
                    status = HttpStatusCode.NotFound
                )
            call.respond(res)
        }
        post {
            log("post")
            val res = call.receive<Result>()
            resultStorage.add(res)
            call.respondText("Res stored correctly", status = HttpStatusCode.Created)

        }
        delete("{id?}") {
            log("delete")
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (resultStorage.removeIf { it.id == id.toInt() }) {
                call.respondText("Res removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }
        }
    }
}

fun log(method: String) {

    println("Starting $method")
    resultStorage.forEach { println(it) }
}