package org.solai.game_factory


import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

//@Configuration
//internal class LoadDatabase {
//    val log = logger()
//
//    @Bean
//    fun initDatabase(repository: RunningGamesRepository): CommandLineRunner {
//
//        return CommandLineRunner { args: Array<String> ->
//            log.info("Preloading " + repository.save(RunningGame("aaa",
//                    mutableListOf("bbb", "ggg"),
//                    true, "ccc")))
//            log.info("Preloading " + repository.save(RunningGame("AAA", mutableListOf("BBB", "GGG"), "CCC")))
//        }
//    }
//
//}