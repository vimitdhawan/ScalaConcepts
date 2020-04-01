package com.vimit.typing.system

object Variance extends App {

  trait Animal

  class Dog extends Animal
  class Cat extends Animal

  class crocodile extends Animal

  class ICage[T]

  class CCage[+T]

  // covariance
  val cCage:  CCage[Animal] = new CCage[Cat]

  //invariance
  // val icage: ICage[Animal] = new ICage[Cat]


}
