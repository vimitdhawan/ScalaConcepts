package com.vimit.implicits

object ImplicitlyConcept extends App {

  case class Permission(value: String)

  implicit val permission: Permission = Permission("vimit")

  val params = implicitly[Permission]
  println(params)



}
