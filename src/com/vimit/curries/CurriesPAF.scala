package com.vimit.curries

object CurriesPAF extends App {

  def add(x: Int) = x+1

  val sum = (x: Int) => x+1

  println(List(1,2,3).map(add))

  def concatinator(a: String, b:String) = a + b

  val conc1 = concatinator("vimit", _: String)

  println(conc1("dhawan"))


}
