package controllers

import play.api._
import play.api.mvc._
import play.api.libs.iteratee.Enumerator

object Application extends Controller {

  def index = Action {
    // リクエストを参照しない。
    Ok("Hello world")
  }

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
    // http://localhost:9000/hello?name=hoge
    // => Hello hoge
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

}
