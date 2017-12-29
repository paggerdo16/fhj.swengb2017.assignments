package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.model._
import org.scalacheck.Prop
import org.scalatest.WordSpecLike
import org.scalatest.prop.Checkers


class BattleShipProtocolSpec extends WordSpecLike {

  import at.fhj.swengb.apps.battleship.model.BattleshipGameGen._

  "BattleShipProtocol" should {
    "be deserializable" in {
      val battlefield: BattleField = BattleField(10, 10, Fleet(FleetConfig.Standard))
      val expectedGame: BattleShipGame = BattleShipGame(battlefield, x => x.toDouble, x => x.toDouble, x => (), x=>())
      expectedGame.hit(BattlePos(1,2))
      val actualGame: BattleShipGame = BattleShipProtocol.convert(BattleShipProtocol.convert(expectedGame))

      val expectedVessel: Vessel = Vessel(NonEmptyString("Destroyer"),BattlePos(2,3),Vertical,5)
      val actualVessel: Vessel = BattleShipProtocol.convert(BattleShipProtocol.convert(expectedVessel))

      val expectedBattlePos = BattlePos(1,2)
      val actualBattlePos = BattleShipProtocol.convert(BattleShipProtocol.convert(expectedBattlePos))



      assert(actualGame.battleField == expectedGame.battleField)
      assert(actualGame.alreadyClicked == expectedGame.alreadyClicked)
      assert(actualVessel == expectedVessel)
      assert(actualBattlePos == expectedBattlePos)


    }
  }
}
