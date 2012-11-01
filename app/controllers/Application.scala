package controllers

import play.api.mvc._

object Application extends Controller {

	def index = Action {
		import play.api.Play.current
		import com.googlecode.mapperdao.utils.Setup

		val (jdbc, mapperdao, queryDao, txManager) = Setup.h2(play.api.db.DB.getDataSource(), List(models.UserEntity))

		val now = new java.util.Date
		mapperdao.insert(models.UserEntity, new models.User(1L, "__name__", now, now))

		val selected = mapperdao.select(models.UserEntity, 1L)
		println("%s".format(selected))

		Ok(jp.furyu.play.velocity.mvc.VM("vm/index.vm"))
	}

}