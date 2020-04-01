package com.vimit.implicits

object Pimping extends App {

  case class Person(name: String,  age: Int)

  implicit class RichPerson(person: Person) {
    def equal(otherPerson: Person) = person.age == otherPerson.age

  }

  val vimit = Person("vimit",  10)
  val ram = Person("ram", 10)
  println(ram.equal(vimit))

  // type 3

  case class User(name: String)

  trait Serializer[T]{
    def serialize(value: T): String
  }

  implicit class WrapSerailize[T](value: T) {
     def toSerialize(implicit serializer: Serializer[T] ) = serializer.serialize(value)
  }

   implicit object UserSerialize extends Serializer[User]{
     override def serialize(user: User): String = user.toString
   }

  val  user = new User("Vimit")
  print(user.toSerialize)

  implicit object InitSerialize extends Serializer[Int]{
    override def serialize(value: Int): String = value.toString
  }

  println(2 toSerialize)

}
