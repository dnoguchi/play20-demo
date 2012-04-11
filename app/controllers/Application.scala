package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator

object Application extends Controller {

  def echo = Action { request =>
    Ok("Got request [" + request + "]")
    // http://localhost:9000/echo?msg=hello
    // => Got request [GET /echo?msg=hello]
  }

  def echo2 = Action { implicit request =>
    // 暗黙的な引数として，別のAPIに渡すこともできる。
    // ここでは，あまり美味しくない。
    Ok("Got request [" + request + "]")
  }

  def hello(name: String) = Action {
    Ok("Hello " + name)
    // http://localhost:9000/hello/hoge
    // => Hello hoge
  }

  def helloBob = Action {
    Redirect(controllers.routes.Application.hello("Bob"))
    // http://localhost:9000/bob
    // => Redirect to http://localhost:9000/hello/Bob
    // => Hello Bob
  }

  def simpleResult = Action {
    // play.api.mvc.SimpleResult
    SimpleResult(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "text/plain")),
      // import play.api.libs.iteratee.Enumerator が必要。
      body = Enumerator("Hello world!")
    )
  }

  // 実装予定のアクションを TODO にしておく。
  def todo = TODO

  def redirect = Action {
    Redirect("http://www.google.co.jp")
  }

  // 可変長引数にしなくてよい。
  def download(name: String) = Action {
    Ok("name: %s".format(name))
    // http://localhost:9000/files/images/logo.png
    // => name: images/logo.png
  }

  def show(page: String) = Action {
    dummy(page).map { htmlContent =>
      Ok(htmlContent).as("text/html")
    }.getOrElse(NotFound)
  }

  private def dummy(page: String) = {
    page match {
      case "none" => None
      case _ => Option(<h2>{page}</h2>)
    }
  }
}
