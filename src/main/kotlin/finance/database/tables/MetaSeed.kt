package finance.database.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

object MetaSeed : Table("meta_seed") {
    val id = integer("id").autoIncrement()
    val label = varchar("label", 255)
    // "group" is a reserved SQL keyword — using group_name as the actual
    // column name avoids escaping headaches, but the Kotlin property below
    // is still named `group` for readability in your seed code.
    val group = varchar("group_name", 255)
    val createdBy = varchar("created_by", 100).nullable()
    val createdAt = datetime("created_at")

    override val primaryKey = PrimaryKey(id)
}