package finance.database.seed

import finance.database.seed.seeds.InitialUserSeed
import finance.database.tables.MetaSeed
import finance.utility.InitializerName
import finance.utility.Logger
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

object SeedRunner {

    private const val logName = InitializerName.SEED_RUNNER

    // Add or remove seeds here — this is your single source of truth
    private val seeds: List<Seed> = listOf(
        InitialUserSeed
    )

    fun run() {
        transaction {
            SchemaUtils.create(MetaSeed)

            seeds.forEach { seed ->
                val alreadyRan = MetaSeed
                    .selectAll()
                    .where { MetaSeed.label eq seed.label }
                    .any()

                if (alreadyRan) {
                    Logger.log("$logName SKIP: '${seed.label}' already ran")
                    return@forEach
                }

                Logger.log("$logName START: Running seed '${seed.label}'")
                seed.run()

                MetaSeed.insert {
                    it[label] = seed.label
                    it[group] = seed.group
                    it[createdBy] = null
                    it[createdAt] = LocalDateTime.now()
                }

                Logger.log("$logName END: Seed '${seed.label}' complete")
            }
        }
    }
}