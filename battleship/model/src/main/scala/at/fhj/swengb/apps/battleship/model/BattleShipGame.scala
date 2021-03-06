package at.fhj.swengb.apps.battleship.model

import javafx.event.Event
import javafx.scene.input.MouseEvent

/**
  * Contains all information about a battleship game.
  */
case class BattleShipGame(battleField: BattleField,
                          getCellWidth: Int => Double,
                          getCellHeight: Int => Double,
                          log: String => Unit,
                          updateSlider: Int => Unit) {

  /**
    * remembers which vessel was hit at which position
    * starts with the empty map, meaning that no vessel was hit yet.
    *
    **/
  var hits: Map[Vessel, Set[BattlePos]] = Map()


  var alreadyClicked : List[BattlePos] = List()

  /**
    * contains all vessels which are destroyed
    */
  var sunkShips: Set[Vessel] = Set()

  /**
    * We don't ever change cells, they should be initialized only once.
    */
  private val cells: Seq[BattleFxCell] = for {x <- 0 until battleField.width
                                              y <- 0 until battleField.height
                                              pos = BattlePos(x, y)} yield {
    BattleFxCell(BattlePos(x, y),
      getCellWidth(x),
      getCellHeight(y),
      log,
      battleField.fleet.findByPos(pos),
      updateGameState,
      hit)
  }

  def hit(pos:BattlePos):Unit = {
    if(!alreadyClicked.contains(pos))
      alreadyClicked = alreadyClicked :+ pos
    updateSlider(alreadyClicked.length)
  }

  def refresh(x:Int) : Unit = {
    println("refresh")
    alreadyClicked.take(x).foreach((pos) => {
      println("shot " ++ pos.toString)
      cells(pos.x*battleField.width + pos.y).getOnMouseClicked.handle(null)
    })
  }

  def getCells: Seq[BattleFxCell] = cells


  def updateGameState(vessel: Vessel, pos: BattlePos): Unit = {
    log("Vessel " + vessel.name.value + " was hit at position " + pos)

    if (hits.contains(vessel)) {
      val oldPos: Set[BattlePos] = hits(vessel)

      hits = hits.updated(vessel, oldPos + pos)

      hits(vessel).foreach(p => log(p.toString))

      if (oldPos.contains(pos)) {
        log("Position was triggered two times.")
      }

      if (vessel.occupiedPos == hits(vessel)) {
        log(s"Ship ${vessel.name.value} was destroyed.")
        sunkShips = sunkShips + vessel

        if (battleField.fleet.vessels == sunkShips) {
          log("G A M E   totally  O V E R")
        }
      }


    } else {
      hits = hits.updated(vessel, Set(pos))
    }

  }


}
