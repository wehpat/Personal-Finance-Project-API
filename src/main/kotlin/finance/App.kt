package finance

import finance.database.DatabaseFactory
import io.github.cdimascio.dotenv.dotenv
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.event.Level
import finance.routes.healthRoute
import finance.routes.rootRoutes

class App {
    companion object {
        val dotenv = dotenv {
            ignoreIfMissing = true
        }

        val port = dotenv["APP_PORT"]?.toIntOrNull() ?: 8080

        @JvmStatic
        fun main(args: Array<String>) {
            // e.g. initialize SQLite connection/tables here before starting the server

            // Database.connect("jdbc:sqlite:app.db", driver = "org.sqlite.JDBC")
            DatabaseFactory().init()

            embeddedServer(Netty, port = port, module = Application::module)
                .start(wait = true)
        }
    }
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
        })
    }

    install(CallLogging) {
        level = Level.INFO
    }

    routing {
        rootRoutes()
        healthRoute()
    }
}