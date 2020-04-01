package com.vimit.implicits

object TypeClass extends App{

  trait Serializable[T, U] {
    def serialize(value: T): U
    }


  object Serializable {
    def serialize[T, U](value: T) (implicit serializable: Serializable[T, U])  = {
      serializable.serialize(value)
    }
  }

  implicit object IntSerializable extends Serializable[Int, String]{

    override def serialize(value: Int): String = s"value is $value"
  }

  print(Serializable.serialize(10))



}
