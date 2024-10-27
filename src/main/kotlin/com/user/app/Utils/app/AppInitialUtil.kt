package com.user.app.Utils.app

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AppInitialUtil(
        private val h2dbservice: h2dbservice
) {
    companion object {
        protected var logger = LoggerFactory.getLogger(AppInitialUtil::class.java)
    }
    @PostConstruct
    fun init() {
        logger.info("START ===================================================================================================")
        h2dbservice.addData()
        logger.info("End   ===================================================================================================")
    }
}
