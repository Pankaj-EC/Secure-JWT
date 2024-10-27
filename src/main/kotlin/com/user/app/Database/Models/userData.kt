package com.user.app.Database.Models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "userData")
data class userData(
        @Id
        @Column(name = "userId")
        var userId:String="",
        @Column(name = "password")
        var password:String="",
        @Column(name = "name")
        var name:String=""
)
