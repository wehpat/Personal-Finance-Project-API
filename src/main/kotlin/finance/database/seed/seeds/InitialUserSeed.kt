package finance.database.seed.seeds

import finance.database.seed.Seed
import finance.database.tables.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime


object InitialUserSeed: Seed {
    override val label = "Initial_User"
    override val group = "Users"

    private data class SeedUser(
        val firstName: String,
        val lastName: String,
        val nickname: String? = null,
        val email: String? = null
    )

    private val initialUsers = listOf(
        SeedUser(firstName = "YOUR_USER1_FN", lastName = "YOUR_USER1_LN", nickname = "YOUR_USER1_NN", email = "YOUR_USER1_EMAIL"),
        SeedUser(firstName = "YOUR_USER2_FN", lastName = "YOUR_USER2_LN", nickname = "YOUR_USER2_NN", email = "YOUR_USER2_EMAIL"),
        SeedUser(firstName = "YOUR_USER3_FN", lastName = "YOUR_USER3_LN", nickname = "YOUR_USER3_NN", email="YOUR_USER3_EMAIL")
    )

    override fun run() {
        transaction {
            initialUsers.forEach { user ->
                Users.insert {
                    it[firstName] = user.firstName
                    it[lastName] = user.lastName
                    it[nickname] = user.nickname
                    it[email] = user.email
                    it[createdAt] = LocalDateTime.now()
                    it[createdBy] = null
                    it[updatedAt] = null
                    it[updatedBy] = null
                    it[isActive] = true
                }
            }
        }
    }
}