package com.vimit.collection

trait MySet[A] extends(A => Boolean) {

  override def apply(v1: A): Boolean = contains(v1)

  def contains(elem: A): Boolean

  def +(elem: A): MySet[A]

  def ++(anotherSet: MySet[A]): MySet[A]

  def map[B](f: A => B): MySet[B]

  def flatMap[B](f: A => MySet[B]): MySet[B]

  def filter(f: A => Boolean): MySet[A]

  def forEach(f: A => Unit): Unit

  def -(elem: A): MySet[A]

  def --(anotherSet: MySet[A]): MySet[A]

  def &(anotherSet: MySet[A]): MySet[A]
}

class EmptySet[A] extends MySet[A] {

  override def contains(elem: A): Boolean = false

  override def +(elem: A): MySet[A] = new NonEmptySet(elem,this)

  override def ++(anotherSet: MySet[A]): MySet[A] = anotherSet

  override def map[B](f: A => B): MySet[B] = new EmptySet[B]

  override def flatMap[B](f: A => MySet[B]): MySet[B] = new EmptySet[B]

  override def filter(f: A => Boolean): MySet[A] = this

  override def forEach(f: A => Unit): Unit = ()

  override def -(elem: A): MySet[A] = this

  override def --(anotherSet: MySet[A]): MySet[A] = this

  override def &(anotherSet: MySet[A]): MySet[A] = this
}

class NonEmptySet[A](head: A, tail: MySet[A]) extends MySet[A] {

  override def contains(elem: A): Boolean = elem == head || this.tail.contains(elem)

  override def +(elem: A): MySet[A] = {
    if(this contains(elem)) this else new NonEmptySet[A](elem, this)
  }

  override def ++(anotherSet: MySet[A]): MySet[A] = tail ++ anotherSet + head

  override def map[B](f: A => B): MySet[B] = (tail map f) + f(head)

  override def flatMap[B](f: A => MySet[B]): MySet[B] = (tail flatMap f) ++ f(head)

  override def filter(f: A => Boolean): MySet[A] = {
    val filteredTail = tail filter f
    if(f(head)) filteredTail + head else filteredTail
  }

  override def forEach(f: A => Unit): Unit = {
    f(head)
    tail forEach f
  }

  override def -(elem: A): MySet[A] = if(head == elem) tail else tail - elem

  override def --(anotherSet: MySet[A]): MySet[A] = filter(x => !anotherSet.contains(x))

  override def &(anotherSet: MySet[A]): MySet[A] = filter(anotherSet)
}

object MySet {
  def apply[A](values: A*): MySet[A] = {
    def builder(values: Seq[A], acc: MySet[A]): MySet[A] = {
      if(values.isEmpty) acc else builder(values.tail, acc + values.head)
    }
    builder(values.toSeq, new EmptySet[A])
  }
}

object Playground extends App {
  val set = MySet(1,2,3)
  set forEach println
}