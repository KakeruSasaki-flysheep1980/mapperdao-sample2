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
		println("%s".format(selected))

		Ok(jp.furyu.play.velocity.mvc.VM("vm/index.vm"))
	}

}