package controllers

import models._
import play.api.mvc._

object Clients extends Controller {

  def show(id: Long) = Action {
    Client.findById(id).map { client =>
      Ok(views.html.Clients.display(client))
    }.getOrElse(NotFound)
  }

  def list(page: Int) = Action {
    Ok("clients list called. page: %s".format(page))
  }

}

