package org.solai.game_factory;

import sol_game.networked_sol_game.NetworkedSolGameConfig
import sol_game.networked_sol_game.SolGameNetworked


public class GameInstance(
        val gameInstanceFinished: ((gameId: String) -> Unit)? = null
) : Thread() {

    lateinit var game: SolGameNetworked
    lateinit var gameId: String

    fun setup(playerCount: Int, allowObservers: Boolean): GameInstanceData {
        game = SolGameNetworked()
        val gameInfo = game.networkSetup(NetworkedSolGameConfig(playerCount, allowObservers))
        gameId = gameInfo.gameId
        return GameInstanceData(
                gameInfo.gameId,
                gameInfo.gameServerAddress,
                gameInfo.gameServerPort,
                gameInfo.playersKeys.toMutableList(),
                gameInfo.allowObservers,
                gameInfo.observerKey
        )
    }

    override fun run() {
        game.start()
        gameInstanceFinished?.invoke(gameId)
    }

    fun terminate() {
        game.stop()
    }
}
