package com.vimit.lazyevaluation

object LazyEvaluation extends App {

  lazy val x: Int = throw new RuntimeException()
  println(x)

}
