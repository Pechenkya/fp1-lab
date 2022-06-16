import munit.ScalaCheckSuite
import org.scalacheck._

import org.scalacheck.Prop._
import org.scalacheck.Arbitrary.arbitrary

import petro.b.lab1._
import scala.util.Random

class SetSuite extends ScalaCheckSuite {

  given [A: Arbitrary]: Arbitrary[Set[A]] =
    Arbitrary(Gen.listOf(arbitrary[A]).map(Set(_*)))

  property("set constructed with sequence contains it's random element, if Seq is empty, Set == Empty") {
    forAll{
      (xs: Seq[Int]) =>
        if xs.length > 0 then
          Set(xs*).contains(Random.shuffle(xs).head)
        else
          Set(xs*) == Set.Empty
    }
  }


  property("after adding element that already in set, it's size shouldn't increase") {
    forAll{
      (set: Set[Int], x: Int) =>
        if(set.contains(x))
          set.size() == Set.add(set, x).size()
        else
          set.size() == Set.add(set, x).size() - 1
    }
  }

  
  property("set should contain object that was added") {
    forAll {
      (set: Set[Int], x: Int) =>
        Set.add(set, x).contains(x)
    }
  }


  property("set is it's own subset"){
    forAll {
      (set: Set[Int]) =>
        Set.is_subset(set, set)
    }
  }


  property("intersection should return set that smaller than parameters") {
    forAll { 
      (left: Set[Int], right: Set[Int]) =>
        Set.intersect(left, right).size() <= left.size().min(right.size())
    }
  }

}
