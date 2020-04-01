package com.vimit.concurrent

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util._

object FirstFuture extends App {


  def getFirstRunFuture[T](futures: TraversableOnce[Future[T]]) = {
    val promise = Promise[T];
    futures.foreach {
      _.onComplete {
        promise.tryComplete
      }
    }
    promise.future
  }


  val firstFuture = Future {
    Thread.sleep(300)
    42
  }

  val secondFuture = Future {
    Thread.sleep(200)
    45
  }

  val result = getFirstRunFuture(List(firstFuture, secondFuture)).onComplete {
    case Success(value) => print(value)
    case Failure(exception) => print("Failure")
  }
  Thread.sleep(1000)


}
