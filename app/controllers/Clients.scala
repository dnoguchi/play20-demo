package controllers

import models._
import play.api.mvc._

object Clients extends Controller {

  def index = Action { request =>
    request.session.get("connected").map { user =>
      Ok("Hello " + user)
    }.getOrElse {
      Unauthorized("Oops, you are not connected")
    }
  }

  def welcome = Action {
    Ok("Welcome!").withSession(
      "connected" -> "user@gmail.com"
    )
  }

  def show(id: Long) = Action {
    Client.findById(id).map { client =>
      Ok(views.html.Clients.display(client))
    }.getOrElse(NotFound)
  }

  def list(page: Int) = Action {
    Ok("clients list called. page: %s".format(page))
  }

}

