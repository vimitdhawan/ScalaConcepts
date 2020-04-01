package com.vimit.concurrent

import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

object LastFuture extends App {

  def getLastRunFuture[T](futures: TraversableOnce[Future[T]]) = {
    val firstPromise = Promise[T];
    val secondPromise = Promise[T]

    futures.foreach {
      _.onComplete { result =>
        if(! firstPromise.tryComplete(result))
          secondPromise.tryComplete(result)
      }
    }
    secondPromise.future
  }


  val firstFuture = Future {
    Thread.sleep(300)
    42
  }

  val secondFuture = Future {
    Thread.sleep(200)
    45
  }

  val result = getLastRunFuture(List(firstFuture, secondFuture)).onComplete {
    case Success(value) => print(value)
    case Failure(exception) => print("Failure")
  }
  Thread.sleep(1000)

}
