package finance.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object Users : Table("users") {
    val id = integer("id").autoIncrement()
    val firstName = varchar("first_name", 100)
    val lastName = varchar("last_name", 100)
    val nickname = varchar("nickname", 100).nullable()
    val email = varchar("email", 255).nullable()
    val createdAt = datetime("created_at")
    val createdBy = varchar("created_by", 100).nullable()
    val updatedAt = datetime("updated_at").nullable()
    val updatedBy = varchar("updated_by", 100).nullable()
    val isActive = bool("is_active").default(true)

    override val primaryKey = PrimaryKey(id)
}