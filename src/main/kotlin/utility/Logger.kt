package utility

import org.slf4j.LoggerFactory
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicLong

object Logger {
    private val logger = LoggerFactory.getLogger("AppLogger")
    private val lastLogTime = AtomicLong(System.currentTimeMillis())

    private val manilaZone = ZoneId.of("Asia/Manila")
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

    fun log(message: String): String {
        return try {
            val now = System.currentTimeMillis()
            val previous = lastLogTime.getAndSet(now)
            val elapsedMs = now - previous

            val manilaTime = ZonedDateTime.now(manilaZone).format(formatter)

            logger.info("[$manilaTime PHT] [+${elapsedMs}ms] $message")
            message
        } catch (e: Throwable) {
            e.printStackTrace()
            message
        }
    }
}