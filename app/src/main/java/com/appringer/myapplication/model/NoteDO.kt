package com.appringer.myapplication.model

import java.io.Serializable


data class NoteDO(val title: String="",
                  val description: String="",
                  val createdON: Long=0) : Serializable