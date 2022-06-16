import munit.ScalaCheckSuite
import org.scalacheck._

import org.scalacheck.Prop._
import org.scalacheck.Arbitrary.arbitrary

import petro.b.lab1._


class SetSuite extends ScalaCheckSuite {

  given [A: Arbitrary]: Arbitrary[Set[A]] =
    Arbitrary(Gen.listOf(arbitrary[A]).map(Set(_*)))

  property("intersection should return set that smaller than parameters") {
    forAll { 
      (left: Set[Int], right: Set[Int]) =>
        Set.intersect(left, right).size() <= left.size().min(right.size())
    }
  }

}
