package com.vimit.typing.system

object DiamondProblem extends App {

  trait Fruit {
    def name(): Unit
  }

  trait Apple extends Fruit {
    override def name(): Unit = print("Apple")
  }

  trait Orange extends Fruit {
    override def name(): Unit = print("Orange")
  }

  class Diamond extends Apple with Orange {
   // override def name(): Unit = print("Diamond")

  }

  val d:Diamond = new Diamond
  d.name()

  // print the last one

}
