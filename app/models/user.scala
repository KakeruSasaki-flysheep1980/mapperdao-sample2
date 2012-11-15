package models

import java.util.Date
import com.googlecode.mapperdao._

// ドメインモデル
case class User(id: Long, name: String, createdTime: Date, updatedTime: Date) extends BaseModel
