package com.vimit.implicits

import java.util.Date

object JsonSerialization extends App {

  case class User(name: String, age: Int, email: String)
  case class Post(description: String, createdBy: Date)
  case class Feed(user: User, posts: List[Post])

  sealed trait JSONValue {
    def stringify: String
  }

  final case class JSONString(value: String) extends JSONValue {
    override def stringify: String = "\"" + value +  "\""
  }

  final case class JSONInt(value: Int) extends JSONValue {
    override def stringify: String = value.toString
  }

  final case class JSONArray(values: List[JSONValue]) extends JSONValue {
    override def stringify: String = values.map(_.stringify).mkString("[", ",", "]")
  }

  final case class JSONObject(value: Map[String, JSONValue]) extends JSONValue {
    override def stringify: String = value.map {
      case(key, value) => "\"" + key +  "\"" + ":" + value.stringify
    }.mkString("{",",","}")
  }

  trait JSONConverter[T] {
    def convert(value: T): JSONValue
  }

  implicit object INTConverter extends JSONConverter[Int]{
    override def convert(value: Int): JSONValue = JSONInt(value)
  }

  implicit object StringConverter extends JSONConverter[String]{
    override def convert(value: String): JSONValue = JSONString(value)
  }

  implicit object ListConverter extends JSONConverter[List[JSONValue]]{
    override def convert(values: List[JSONValue]): JSONValue = JSONArray(values)
  }

  implicit object UserConverter extends JSONConverter[User] {
    override def convert(value: User): JSONValue = JSONObject(Map(
      "name" -> JSONString(value.name) ,
      "age"  -> JSONInt(value.age),
      "email" -> JSONString(value.email)
    ))
  }

   val vimit:User = User("vimit",1,"vimit0601@live.com")

  implicit class JsonOps[T](value:T) {
    def toJson(implicit converter:JSONConverter[T]): JSONValue ={
      converter.convert(value)
    }
  }

  println(vimit.toJson.stringify)

}
