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
  val css =  "/at/fhj/swengb/apps/calculator/calculator.css"

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
  def getCalculator() : RpnCalculator = calculatorProperty.get()
  def setCalculator(rpnCalculator : RpnCalculator) : Unit = calculatorProperty.set(rpnCalculator)

  @FXML var TextField1 : TextField = _
  @FXML var TextField2 : TextField = _
  @FXML var TextField3 : TextField = _


  override def initialize(location: URL, resources: ResourceBundle) = {

  }

  def calcul() : Unit = {
    getCalculator().push(Op(TextField3.getText)) match {

      case Success(c) => setCalculator(c)
      case Failure(e) => /* warning

      /*  Wenn Stack size kleiner als 2 --> textOne nicht befüllen
      if (getCalculator().stack.size < 2)      {
        (textTwo.setText(getCalculator().stack(0).toString.drop(4).init))
        textThree.setText("")
      }
      else {
      }

    */
    */
    }

    getCalculator().stack foreach println

    (TextField2.setText(getCalculator().stack(0).toString.drop(4).init))
    TextField3.setText("")
    (TextField1.setText(getCalculator().stack(1).toString.drop(4).init))
    TextField3.setText("")
  }

  def eins() : Unit = {
    TextField3.appendText("1")
  }

  def zwei() : Unit = {
    TextField3.appendText("2")
  }

  def drei() : Unit = {
    TextField3.appendText("3")
  }

  def vier() : Unit = {
    TextField3.appendText("4")
  }

  def fünf() : Unit = {
    TextField3.appendText("5")
  }

  def sechs() : Unit = {
    TextField3.appendText("6")
  }

  def sieben() : Unit = {
    TextField3.appendText("7")
  }

  def acht() : Unit = {
    TextField3.appendText("8")
  }

  def neun() : Unit = {
    TextField3.appendText("9")
  }

  def zero() : Unit = {
    TextField3.appendText("0")
  }

  def plus() : Unit = {
    TextField3.appendText("+")
  }

  def minus() : Unit = {
    TextField3.appendText("-")
  }

  def mal() : Unit = {
    TextField3.appendText("*")
  }

  def div() : Unit = {
    TextField3.appendText("/")
  }

  def comma() : Unit = {
    TextField3.appendText(".")
  }

  def sign() : Unit = {
    TextField3.appendText("")
  }

  def sgn() : Unit = {
    println("an event has happend")
  }
}