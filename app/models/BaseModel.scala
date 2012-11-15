package models

import java.util.Date

trait BaseModel extends Sequenced with RecorededTime

trait Sequenced {
	val id: Long
}

trait RecorededTime {
	val createdTime: Date
	val updatedTime: Date
}