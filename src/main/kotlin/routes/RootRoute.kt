package routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.rootRoutes() {
    get("/") {
        call.respondText("Server is running at ${App.port}")
    }
}