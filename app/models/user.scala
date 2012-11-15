package models

import java.util.Date

// ドメインモデル
case class User(id: Long, name: String, createdTime: Date, updatedTime: Date) extends BaseModel
