package org.solai.game_factory

import org.springframework.web.bind.annotation.*


data class CreateGameBody(
        val playerCount: Int,
        val allowObservers: Boolean
)

data class TerminateGameBody(
        val gameId: String
)

data class TerminateGameRes(
        val terminated: Boolean
)


@RestController
class GamesController (
        private val repository: RunningGamesRepository
) {
    val gameInstancePool: GameInstancePool = GameInstancePool()

    @PostMapping("/api/createGame")
    fun createGame(@RequestBody body: CreateGameBody): GameInstanceData {
        return repository.save(gameInstancePool.createGameInstance(
                body.playerCount, body.allowObservers
        ));
    }

    @PostMapping("/api/terminateGame")
    fun terminateGame(@RequestBody body: TerminateGameBody): TerminateGameRes {
        return TerminateGameRes(gameInstancePool.terminateGame(body.gameId));
    }

    @GetMapping("/api/gameAlive/{gameId}")
    fun gameAlive(@PathVariable gameId: String): Boolean {
        return gameInstancePool.isGameAlive(gameId)
    }
}