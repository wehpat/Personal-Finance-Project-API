package finance.database

import finance.database.seed.SeedRunner
import finance.database.tables.Users
import finance.utility.InitializerName
import finance.utility.Logger
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File

class DatabaseFactory {

    private val logName = InitializerName.DATABASE_FACTORY

    fun init() {
        Logger.log("$logName START: Initializing SQLite Database")

        val dbFile = File("data/app.db")
        dbFile.parentFile?.mkdirs()
        Database.connect(
            url = "jdbc:sqlite:${dbFile.absolutePath}",
            driver = "org.sqlite.JDBC"
        )

        transaction {
            SchemaUtils.create(Users)
        }

        SeedRunner.run()

        Logger.log("$logName END: Initializing SQLite Database at ${dbFile.absolutePath}")
    }
}