package com.vimit.implicits

object OrganizingImports extends App {

  implicit val ordering: Ordering[Int] = Ordering.fromLessThan(_ > _)

  val list = List(5,4,2,1,3).sorted
  print(list)

}
