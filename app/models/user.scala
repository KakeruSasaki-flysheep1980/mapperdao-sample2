package models

import java.util.Date
import com.googlecode.mapperdao._

// ドメインモデル
case class User(id: Long, name: String, createdTime: Date, updatedTime: Date)

// ドメインモデルとデータベースのマッピングを責務とするクラス
// テーブル名, 列名はここで定義可能
object UserEntity extends Entity[Long, NaturalLongId, User]("User") {
	val id = key("id") to (_.id)
	val name = column("name") to (_.name)
	val createdTime = column("created_time") to (_.createdTime)
	val updatedTime = column("updated_time") to (_.updatedTime)
	def constructor(implicit m: com.googlecode.mapperdao.ValuesMap) = new User(id, name, createdTime, updatedTime) with NaturalLongId
}