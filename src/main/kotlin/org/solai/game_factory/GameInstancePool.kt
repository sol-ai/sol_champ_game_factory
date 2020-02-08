package org.solai.game_factory

import sol_game.networked_sol_game.NetworkedSolGameConfig
import sol_game.networked_sol_game.SolGameNetworked
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.stream.IntStream
import kotlin.streams.toList
class GameInstancePool {

    val runningGameInstances: ConcurrentMap<String, GameInstance> = ConcurrentHashMap()

    fun isGameAlive(gameId: String): Boolean {
        return runningGameInstances.containsKey(gameId)
    }

    fun terminateGame(gameId: String): Boolean {
        val game = runningGameInstances[gameId]
        if (game != null) {
            game.terminate()
            game.join()
            runningGameInstances.remove(gameId)
            return true
        }
        return false
    }

    fun createGameInstance(playerCount: Int, allowObservers: Boolean): GameInstanceData {

        val gameInstance = GameInstance() {gameId ->
            terminateGame(gameId)
        }
        val gameData = gameInstance.setup(playerCount, allowObservers)
        runningGameInstances[gameData.gameId] = gameInstance
        gameInstance.start()

        return gameData
    }
}