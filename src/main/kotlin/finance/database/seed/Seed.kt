package finance.database.seed

interface Seed {
    val label: String
    val group: String
    fun run()
}