package controllers

import play.api.mvc._
import daos.Daos._
import models._

object Application extends Controller {

	def index = Action {
		val now = new java.util.Date
		val userDao = new UserDao

		val created = userDao.create(new User(2L, "__name__", now, now))
		println("======== created: %s".format(created))

		val retrieved = userDao.retrieve(2L)
		println("======== retrieved: %s".format(retrieved))

		println("======== findByName: %s".format(userDao.findByName("__name__")))

		val updated = userDao.update(retrieved.get, new User(2L, "__updated__", now, now))
		println("======== updated: %s".format(updated))

		userDao.delete(2L)
		println("======== retrieved: %s".format(userDao.retrieve(2L)))

		Ok
	}

}
