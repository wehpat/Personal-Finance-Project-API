package routes

import kotlinx.serialization.Serializable
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.json.JSONObject
import utility.Logger

@Serializable
data class HealthCheckRequest(
    val message: String,
    val timestamp: Long? = null
)

fun Route.healthRoute() {
    get("/health") {
        Logger.log("START: CHECK HEALTH Route")
        call.respond(mapOf("status" to "200 -- GET"))
        Logger.log("START: CHECK HEALTH Route")
    }

    post("/health") {
        Logger.log("START: CHECK HEALTH POST Route")

        val body = call.receive<HealthCheckRequest>()
        val myJsonObject = JSONObject().put("success", 1).put("message", "JSON Object is working")
        println("My JSONOBject: $myJsonObject")
        call.respond(mapOf("status" to "200 -- POST", "received" to body.message))

        Logger.log("END: CHECK HEALTH POST Route")
    }

    put("/health") {
        Logger.log("START: CHECK HEALTH PUT Route")
        val body = call.receive<HealthCheckRequest>()
        call.respond(mapOf("status" to "200 -- PUT", "received" to body.message))
        Logger.log("END: CHECK HEALTH PATCH")
    }

    patch("/health") {
        Logger.log("START: CHECK HEALTH PATCH Route")
        val body = call.receive<HealthCheckRequest>()
        call.respond(mapOf("status" to "200 -- PATCH", "received" to body.message))
        Logger.log("END: CHECK HEALTH PATCH Route")
    }

    delete("/health") {
        Logger.log("START: CHECK HEALTH DELETE Route")
        val body = call.receiveNullable<HealthCheckRequest>()
        call.respond(mapOf("status" to "200 -- DELETE", "received" to (body?.message ?: "no body sent")))
        Logger.log("END: CHECK HEALTH DELETE Route")
    }

    options("/health") {
        Logger.log("START: CHECK HEALTH OPTION Route")
        val body = call.receiveNullable<HealthCheckRequest>()
        call.respond(mapOf("status" to "200 -- DELETE", "received" to (body?.message ?: "no body sent")))
        Logger.log("END: CHECK HEALTH OPTION Route")
    }
}