package models

case class Client(name: String, age: Int)

object Client {

  def findById(id: Long): Option[Client] =
    if(id % 2 == 0) Option(Client("hoge", id.toInt)) else None

}
