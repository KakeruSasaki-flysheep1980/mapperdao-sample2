package models

import java.util.Date

trait BaseModel extends Sequenced with RecordedTime

trait Sequenced {
	val id: Long
}

trait RecordedTime {
	val createdTime: Date
	val updatedTime: Date
}