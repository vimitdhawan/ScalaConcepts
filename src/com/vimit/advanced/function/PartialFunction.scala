package com.vimit.advanced.function

object PartialFunction extends App {

  val aFunction = (value: Int) => value+1

  val aRestrictedFunction = (value: Int) => value match {
    case 42 => 70
  }

  val aPartialFunction: PartialFunction[Int, Int]  = {
    case 1 => 42
  }

  val liftedFunction = aPartialFunction.lift

  println(liftedFunction(2)) // None
  println(liftedFunction(1)) // 42

  val apfChain = aPartialFunction.orElse[Int, Int]{
    case 2 => 5
  }

  val multipleAction = aPartialFunction.andThen[Int]{
    case 42 => 70
  }

  println(apfChain(2))
  println(multipleAction(1))

}
