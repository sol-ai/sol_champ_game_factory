package org.solai.game_factory

import lombok.Data
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.Id



@Data
@Entity
class GameInstanceData (
        @Id
        val gameId: String = uuid(),
        val gameServerAddress: String = "",
        val gameServerPort: Int = -1,
        @ElementCollection
        val playersKeys: MutableList<String> = ArrayList(),
        val allowObservers: Boolean = false,
        val observerKey: String? = if (allowObservers) uuid() else null
)
