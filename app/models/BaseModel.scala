package models

import java.util.Date

trait BaseModel {
	val id: Long
	val createdTime: Date
	val updatedTime: Date
}