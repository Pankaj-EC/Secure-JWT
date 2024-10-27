package com.user.app.Database.Repository

import com.user.app.Database.Models.userData
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface userRepo: JpaRepository<userData, String> {
    fun findByUserId(userId: String): userData
}