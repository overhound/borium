import java.io.File
import java.util.*


// Get the passed in path, i.e. "-d some/path" or use the current path.
val path = when {
    args.contains("-d") -> args[1 + args.indexOf("-d")]
    args.contains("-c") -> args[1 + args.indexOf("-c")]
    else -> "."
}

val simulatedTime = when {
    args.contains("-t") -> args[1 + args.indexOf("-t")]
    else -> "${simulatedCalendar()[Calendar.HOUR_OF_DAY]}:${simulatedCalendar()[Calendar.MINUTE]}"
}

var timeString = ""
var minute = ""
var hour = ""

readLines(path)


fun readLines(fileName: String) = File(fileName).forEachLine { getCycleRun(it) }

fun getCycleRun(command: String) {
    extractTime(command)
    var outputString = ""
    var day = ""
    val timeToLaunch = simulatedCalendar()
    val minute = getMinute()
    val hour = getHour()
    timeToLaunch[Calendar.HOUR_OF_DAY] = hour
    timeToLaunch[Calendar.MINUTE] = minute
    when {
        simulatedTime.simulatedTimeHour() <= timeToLaunch[Calendar.HOUR_OF_DAY] -> day = "today - "
        simulatedTime.simulatedTimeHour() > timeToLaunch[Calendar.HOUR_OF_DAY] -> day = "tomorrow - "
    }
    outputString += "$hour:${minute.padMinute()} "
    outputString += day
    outputString += command.substringAfterLast(" ")
    println(outputString)
}

fun extractTime(command: String) {
    timeString = command.substringBeforeLast(" ")
    minute = timeString.substringBefore(" ")
    hour = timeString.substringAfter(" ")
}

fun simulatedCalendar(): Calendar {
    val cal = Calendar.getInstance()
    cal[Calendar.HOUR_OF_DAY] = simulatedTime.simulatedTimeHour()
    cal[Calendar.MINUTE] = simulatedTime.simulatedTimeMinute()
    return cal
}

fun getHour(): Int {
    val currentTime = simulatedCalendar()
    val currentHour = currentTime[Calendar.HOUR_OF_DAY]

    return when {
        getIsEveryHour() -> currentHour
        !getIsEveryMinute() && (currentTime[Calendar.MINUTE] > minute.toInt()) -> currentHour + 1
        else -> hour.toInt()
    }
}

fun getMinute(): Int {
    val currentTime = simulatedCalendar()

    return when {
        getIsEveryMinute() and getIsEveryHour() -> currentTime[Calendar.MINUTE]
        !getIsEveryHour() and getIsEveryMinute() -> 0
        else -> minute.toInt()
    }
}

fun getIsEveryHour() = hour == "*"

fun getIsEveryMinute() = minute == "*"

fun Int.padMinute() = toString().padStart(2, '0')

fun String.simulatedTimeHour() = substringBefore(":").toInt()
fun String.simulatedTimeMinute() = substringAfter(":").toInt()
