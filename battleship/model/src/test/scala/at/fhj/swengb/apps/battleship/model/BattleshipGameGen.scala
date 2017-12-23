package at.fhj.swengb.apps.battleship.model

import org.scalacheck.Gen


/**
  * Implement in the same manner like MazeGen from the lab, adapt it to requirements of BattleShip
  */
object BattleshipGameGen {

  val maxX: Int = 100
  val maxY: Int = 100

  val battleShipGameGen: Gen[BattleShipGame] =
    for {
      width <- Gen.chooseNum(1, maxX)
      height <- Gen.chooseNum(1, maxY)
      fleet <- fleetGen
      getCellWidth <-
      getCellHeight <-
      log <-
  } yield BattleshipGame(BattleField, getCellWidth, getCellHeight, log)

  val battleFieldGen: Gen[BattleField] =
    for {
      width <- Gen.chooseNum(1, maxX)
      height <- Gen.chooseNum(1, maxY)
      fleet <- fleetGen
  } yield BattleField(width, height, fleet)

  val battleFxCellGen: Gen[BattleFxCell] =
    for {
      pos <- battlePosGen
      width <- Gen.chooseNum(1, maxX)
      heigth <- Gen.chooseNum(1, maxY)
      //log <-
      vessel <- vesselGen
      someVessel <- Gen.oneOf[Option[Vessel]](None, Option(vessel))
      fn <-

  } yield


  val battlePosGen: Gen[BattlePos] = for {
    x <- Gen.chooseNum(1, maxX - 1)
    y <- Gen.chooseNum(1, maxY - 1)
  } yield BattlePos(x, y)

  val fleetGen: Gen[Fleet] =
    for {
      vessel <- vesselGen
  } yield Fleet(vessel)

  val fleetConfigGen: Gen[FleetConfig] = ???

  val vesselGen: Gen[Vessel] =
    for {
      name <- Gen.
      startPos <- battlePosGen
      direction <-
      size <-
    } yield Vessel(name,startPos,direction, size)
}
