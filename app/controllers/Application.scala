package controllers

import play.api.mvc._

object Application extends Controller {

	def index = Action {
		import play.api.Play.current
		import com.googlecode.mapperdao.utils.Setup

		// データベース設定を読み込む
		val (jdbc, mapperdao, queryDao, txManager) = Setup.h2(play.api.db.DB.getDataSource(), List(models.UserEntity))

		// mapperdaoはDDL発行はしてくれない??
		// evolutionで作成する -> play run -> ページアクセスで作成可

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

		Ok(jp.furyu.play.velocity.mvc.VM("vm/index.vm"))
	}

}