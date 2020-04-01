package com.vimit.typing.system

object DuckTyping extends App {

  type javaClosable = {
      def close(): Unit
  }

  type scalaClosable = {
      def close(): Unit
  }

  class UseClosable {
    def close() = println("scala")
  }

  def fun1(closable: scalaClosable):Unit = {
    closable.close()
  }

  fun1(new UseClosable)

}
