package com.vimit.implicits

object ImplicitIntro extends App {

  case class Person(name:String) {
    def greet(): Unit ={
      print(s"Person name ${name}")
    }
  }

  case class Dog(name: String){
    def greet(): Unit ={
      print(s"Dog name ${name}")
    }
  }

  implicit def convertPerson(name: String) = Person(name)
  // implicit def convertDog(name: String) = Dog(name)

  "vimit" greet


}
