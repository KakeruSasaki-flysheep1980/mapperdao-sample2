package daos

import com.googlecode.mapperdao.utils.{ Setup, All, TransactionalCRUD }
import com.googlecode.mapperdao.{ DeclaredIds, Entity, NaturalLongId }
import play.api.Play.current
import play.api.db.DB
import models._
import daos.Entities._
import com.googlecode.mapperdao.Query._

object Daos {

	// データベース設定を読み込む
	// TODO BaseEntityを実装しているクラスを動的に取得してもいいかも??
	private val (jdbc, md, qd, txm) = Setup.h2(DB.getDataSource(), List(UserEntity))

	// mapperdaoはDDL発行はしてくれない??
	// evolutionで作成する -> play run -> ページアクセスで作成可

	trait BaseDao[PC <: DeclaredIds[Long], T <: BaseModel] extends TransactionalCRUD[Long, PC, T] with All[Long, PC, T] {
		val queryDao = qd
		val mapperDao = md
		val txManager = txm

		protected val entity: BaseEntity[PC, T]
	}

	// DAO for User
	class UserDao extends BaseDao[NaturalLongId, User] {
		protected val entity = UserEntity
		def findByName(name: String): List[User] = queryDao.query(select from entity where entity.name === name orderBy entity.id)
	}

}
