package com.vimit.StackOverflow

import scala.concurrent._
import ExecutionContext.Implicits.global


object FutureProblem extends App {


  val list = List(1, 2, 3)
  val outputList = List()

  val result = for {
    value <- list
  } yield {
    for {
      result <- getValue(value).map(res => outputList ++ List(value))
    } yield result
  }

  print(result)

  def getValue(value: Int) = Future(value)

}

