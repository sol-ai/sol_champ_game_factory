package org.solai.game_factory

import org.springframework.data.jpa.repository.JpaRepository


interface RunningGamesRepository : JpaRepository<GameInstanceData, Long> {

}