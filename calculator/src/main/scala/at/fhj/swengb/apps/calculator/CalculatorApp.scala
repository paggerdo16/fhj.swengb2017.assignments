package at.fhj.swengb.apps.calculator

import java.net.URL
import java.util.ResourceBundle
import javafx.application.Application
import javafx.beans.property.{ObjectProperty, SimpleObjectProperty}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.control.TextField
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Success}
import scala.util.control.NonFatal

object CalculatorApp {

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[CalculatorFX], args: _*)
  }
}

class CalculatorFX extends javafx.application.Application {

  val fxml = "/at/fhj/swengb/apps/calculator/calculator.fxml"
  val css = "/at/fhj/swengb/apps/calculator/calculator.css"

  def mkFxmlLoader(fxml: String): FXMLLoader = {
    new FXMLLoader(getClass.getResource(fxml))
  }

  override def start(stage: Stage): Unit =
    try {
      stage.setTitle("Calculator")
      setSkin(stage, fxml, css)
      stage.show()
      stage.setMinWidth(stage.getWidth)
      stage.setMinHeight(stage.getHeight)
    } catch {
      case NonFatal(e) => e.printStackTrace()
    }

  def setSkin(stage: Stage, fxml: String, css: String): Boolean = {
    val scene = new Scene(mkFxmlLoader(fxml).load[Parent]())
    stage.setScene(scene)
    stage.getScene.getStylesheets.clear()
    stage.getScene.getStylesheets.add(css)
  }

}

class CalculatorFxController extends Initializable {

  val calculatorProperty: ObjectProperty[RpnCalculator] = new SimpleObjectProperty[RpnCalculator](RpnCalculator())

  def getCalculator(): RpnCalculator = calculatorProperty.get()

  def setCalculator(rpnCalculator: RpnCalculator): Unit = calculatorProperty.set(rpnCalculator)

  @FXML var Text1: TextField = _
  @FXML var Text2: TextField = _
  @FXML var Textlast: TextField = _

  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def one(): Unit = {
    Text1.appendText("1")
  }

  def two(): Unit = {
    Text1.appendText("2")
  }

  def three(): Unit = {
    Text1.appendText("3")

  }

  def four(): Unit = {
    Text1.appendText("4")

  }

  def five(): Unit = {
    Text1.appendText("5")

  }

  def six(): Unit = {
    Text1.appendText("6")

  }

  def seven(): Unit = {
    Text1.appendText("7")

  }

  def eight(): Unit = {
    Text1.appendText("8")

  }

  def nine(): Unit = {
    Text1.appendText("9")

  }

  def zero(): Unit = {
    Text1.appendText("0")

  }

  def plus(): Unit = {
    if (Text1.getText.isEmpty) {
      if (getCalculator().stack.size < 2) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        Text1.setText("+")
        enter()
      }
    } else {
      if (getCalculator().stack.size < 1) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        enter()
        Text1.setText("+")
        enter()
      }
    }
  }

  def minus(): Unit = {
    if (Text1.getText.isEmpty) {
      if (getCalculator().stack.size < 2) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        Text1.setText("-")
        enter()
      }
    } else {
      if (getCalculator().stack.size < 1) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        enter()
        Text1.setText("-")
        enter()
      }
    }
  }

  def divide(): Unit = {
    if (Text1.getText.isEmpty) {
      if (getCalculator().stack.size < 2) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        Text1.setText("/")
        enter()
      }
    } else {
      if (getCalculator().stack.size < 1) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        enter()
        Text1.setText("/")
        enter()
      }
    }
  }

  def multiply(): Unit = {
    if (Text1.getText.isEmpty) {
      if (getCalculator().stack.size < 2) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        Text1.setText("*")
        enter()
      }
    } else {
      if (getCalculator().stack.size < 1) {
        Textlast.setText("Zu wenig Zahlen sind am Stack")
      } else {
        enter()
        Text1.setText("*")
        enter()
      }
    }
  }

  def enter(): Unit = {
    if (Text1.getText.nonEmpty) {
      getCalculator().push(Op(Text1.getText)) match {
        case Success(s) => setCalculator(s)
        case Failure(f) => println(f.toString)
      }
      if (getCalculator().stack.size == 1) {
        Textlast.setText("")
        Text1.setText("")
        Text2.setText(getCalculator().stack.last.toString.init.drop(4))
      } else {
        Text1.setText("")
        Text2.setText(getCalculator().stack.last.toString.init.drop(4))
        Textlast.setText(getCalculator().stack.init.last.toString.init.drop(4))
      }
    } else {
      Textlast.setText("Zuerst muss eine Zahl eingegeben werden!")
    }
  }

  def point(): Unit = {
    if (Text1.getText.isEmpty || Text1.getText.contains(".")) {
      Text1.setText(Text1.getText)
    } else {
      Text1.setText(Text1.getText + ".")
    }
  }

  def clear(): Unit = {
    if (Text1.getText.isEmpty) {
      Text2.setText("")
      Textlast.setText("")
      setCalculator(RpnCalculator())
    } else {
      Text1.setText("")
    }
  }

  def invert(): Unit = {
    if (Text1.getText.isEmpty) {
      Textlast.setText("Zuerst muss eine Zahl eingegeben werden!")
    } else if (Text1.getText.head == '-') {
      Text1.setText(Text1.getText.tail)
    } else {
      Text1.setText("-" + Text1.getText)

    }
  }
}
