# Offline Coding Test


This test submissioin was written with **kotlin scripts** `.kts`.

To run this script execute this command in the terminal from project root:

`kotlinc -script src/main/kotlin/ScheduleJob.kts -- -c src/main/resources/test.txt -t <inputTime>`

Alternatively, the command can be run from anywhere given that the absolute paths are used for the relevant files i.e: 

`kotlinc -script <path to ScheduleJob.kts> -- -c <path to {input.txt}> -t <inputTime>`

This project also contains a test case to test against the standard sample data included in the test description.



