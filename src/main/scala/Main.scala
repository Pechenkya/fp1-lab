package petro.b.lab1

import scala.annotation.tailrec

enum Set[+A]:
  case Empty
  case NonEmpty private[Set] (a: A, rest: Set[A])


object Set:
  def makeSet[A](xs: A*): Set[A] = ???
    // if xs.isEmpty then
    //   Empty
    // else 
    //   (xs., makeSet[]())
    
  def add[A](s: Set[A], a: A): Set[A] = 
    if contains(s, a) then 
      s
    else
      NonEmpty(a, s)

  def intersect[A](l: Set[A], r: Set[A]): Set[A] = ???

  def union[A](l: Set[A], r: Set[A]): Set[A] = ???

  @tailrec
  def contains[A](s: Set[A], a: A): Boolean = 
    s match
      case Empty => false
      case NonEmpty(b, set) => if(a == b) then true else contains(set, a)

  def map[A, B](b: Set[A], f: A => B): Set[B] = ???

@main def hello: Unit = 
  print("Still compiling :)")