package daos

import com.googlecode.mapperdao.{ DeclaredIds, NaturalLongId, Entity, ValuesMap }
import models._

object Entities {

	// ドメインモデルとデータベースのマッピングを責務とするクラス
	// テーブル名, 列名はここで定義可能
	// ID列をauto_incrementするか否かはNaturalLongIdを指定するかどうかってことかな??
	object UserEntity extends BaseEntity[NaturalLongId, User] {
		val name = column("name") to (_.name)
		def constructor(implicit m: ValuesMap) = new User(id, name, createdTime, updatedTime) with NaturalLongId
	}

}

trait BaseEntity[PC <: DeclaredIds[Long], T <: BaseModel] extends Entity[Long, PC, T] {
	val id = key("id") to (_.id)
	val createdTime = column("created_time") to (_.createdTime)
	val updatedTime = column("updated_time") to (_.updatedTime)
}
