package controllers

import play.api.mvc._
import com.googlecode.mapperdao.{ NaturalLongId, QueryDao, MapperDao }
import com.googlecode.mapperdao.utils.{ TransactionalCRUD, All, TransactionalSurrogateLongIdCRUD }

object Application extends Controller {

	import play.api.Play.current
	import com.googlecode.mapperdao.utils.Setup

	// データベース設定を読み込む
	val (jdbc, mapperdao, queryDao, txManager) = Setup.h2(play.api.db.DB.getDataSource(), List(models.UserEntity))

	// mapperdaoはDDL発行はしてくれない??
	// evolutionで作成する -> play run -> ページアクセスで作成可

	def index = Action {
		// テキトーにデータ登録
		val now = new java.util.Date
		mapperdao.insert(models.UserEntity, new models.User(1L, "__name__", now, now))

		// テキトーに検索
		val selected = mapperdao.select(models.UserEntity, 1L)
		println("selected: %s".format(selected))

		// テキトーに更新
		val updated = mapperdao.update(models.UserEntity, selected.get, new models.User(1L, "__updated__", now, now))
		println("updated: %s".format(mapperdao.select(models.UserEntity, 1L)))

		// テキトーに削除
		mapperdao.delete(models.UserEntity, updated)
		println("deleted: %s".format(mapperdao.select(models.UserEntity, 1L)))

		Ok
	}

	def index2 = Action {
		val now = new java.util.Date
		val userDao = new UserDao

		val created = userDao.create(new models.User(2L, "__name__", now, now))
		println("created: %s".format(created))

		val retrieved = userDao.retrieve(2L)
		println("retrieved: %s".format(retrieved))

		val updated = userDao.update(retrieved.get, new models.User(2L, "__updated__", now, now))
		println("updated: %s".format(updated))

		userDao.delete(2L)
		println("retrieved: %s".format(userDao.retrieve(2L)))

		Ok
	}

	class UserDao extends TransactionalCRUD[Long, NaturalLongId, models.User] with All[Long, NaturalLongId, models.User] {
		val entity = models.UserEntity
		val queryDao = Application.queryDao
		val mapperDao = Application.mapperdao
		val txManager = Application.txManager
	}

}