package at.fhj.swengb.apps.calculator

import java.nio.file.{Files, Paths}
import java.util

import org.scalatest.WordSpecLike

import scala.collection.JavaConverters._

class TimesheetSpec extends WordSpecLike {


  val file = "C:\\Workspace\\fhj.swengb2017.assignments\\calculator\\timesheet-calculator.adoc"
  val new_file = Paths.get(file)

  val timesheet = """== Time expenditure: Calculator assignment
                    |
                    |
                    |[cols="1,1,1,4", options="header"]
                    |.Time expenditure
                    ||===
                    || Date
                    || Hours (planned)
                    || Hours (spend)
                    || Description
                    |
                    || 04.12.17
                    || 2
                    || 1
                    || Create Buttons
                    |
                    || 04.12.17
                    || 1
                    || 1.5
                    || Read this and that
                    |
                    || 11.07.17
                    || --
                    || --
                    || --
                    |
                    |
                    |
                    ||===
                    |""".stripMargin

  "Timetable" should {
    "be not the same as expected" in {
      assert(println(Files.readAllLines(new_file)) != timesheet)
    }

  }
}
