import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ScheduleJobTest {

    private fun runCommand(command: String) = Runtime.getRuntime()
        .exec(arrayOf("/bin/sh", "-c", command))
        .inputStream.bufferedReader().readText()

    @Test
    fun testExampleData() {
        val inputTime = "16:10"
        val result =
            runCommand("kotlinc -script src/main/kotlin/ScheduleJob.kts -- -c src/main/resources/test.txt -t $inputTime")
        assertEquals(expected,result)

    }

    private val expected = "1:30 tomorrow - /bin/run_me_daily\n" +
            "16:45 today - /bin/run_me_hourly\n" +
            "16:10 today - /bin/run_me_every_minute\n" +
            "19:00 today - /bin/run_me_sixty_times\n"
}