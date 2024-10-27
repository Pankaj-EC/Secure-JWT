package com.user.app.Database.Repository

import com.user.app.Database.Models.loginRecode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface loginRecodeRepository:JpaRepository<loginRecode,String> {
}