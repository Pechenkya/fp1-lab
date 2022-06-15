package petro.b.lab1

import scala.annotation.tailrec

enum Set[+A]:
  case Empty
  case NonEmpty private (a: A, rest: Set[A])


object Set:
  def makeSet[A](xs: A*): Set[A] = ???

  def add[A](s: Set[A], a: A): Set[A] = ???

  def intersect[A](l: Set[A], r: Set[A]): Set[A] = ???

  def union[A](l: Set[A], r: Set[A]): Set[A] = ???

  def contains[A](s: Set[A], a: A): Boolean = ???

  def map[A, B](b: Set[A], f: A => B): Set[B] = ???

@main def hello: Unit = 
  print("Still compiling :)")