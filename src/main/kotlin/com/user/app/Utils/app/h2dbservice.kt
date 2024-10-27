package com.user.app.Utils.app

import com.user.app.Database.Models.userData
import com.user.app.Database.Repository.userRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class h2dbservice {

    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var userRepo: userRepo

    fun createTable() {
        // Create usrDetails table
        jdbcTemplate.execute("CREATE TABLE userData (userId VARCHAR(10), password VARCHAR(20))")
        println("Table usrDetails created")
    }

    fun addData() {
        val user1 = userData("001100", "p@ssw0rd","Ram")
        val user2 = userData("003300", "p@ssw0rd","Krishna")
        userRepo.save(user1)
        userRepo.save(user2)
        println("Data added to usrDetails table")
    }
}
