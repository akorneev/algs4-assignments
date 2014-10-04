import java.util.NoSuchElementException

import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

import scala.collection.JavaConversions._
import scala.language.implicitConversions

class RandomizedQueueSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  val genDequeue: Gen[Operation] = Gen.const(Dequeue)
  val genEnqueue: Gen[Operation] = for (i <- arbitrary[Int]) yield Enqueue(i)
  val genOperation: Gen[Operation] = Gen.frequency((9, genEnqueue), (1, genDequeue))
  implicit val arbOperation: Arbitrary[Operation] = Arbitrary(genOperation)
  sealed trait Operation
  case class Enqueue(value: Int) extends Operation
  case object Dequeue extends Operation

  describe("A RandomizedQueue") {
    it("should have right size") {
      forAll { (input: Seq[Int]) =>
        val queue = new RandomizedQueue[Int]
        input foreach queue.enqueue
        queue.size should equal(input.size)
        if (input.isEmpty) queue should be('empty)
        else queue should not be ('empty)
      }
    }
    it("should dequeue all enqueued elements") {
      forAll { (input: Seq[Int]) =>
        val queue = new RandomizedQueue[Int]
        input foreach queue.enqueue
        var dequeued: Seq[Int] = Seq.empty
        try while (true) {
          dequeued = dequeued :+ queue.dequeue()
        } catch {case _: NoSuchElementException =>}
        dequeued.sorted should equal(input.sorted)
      }
    }
    it("`sample()` should return random enqueued item") {
      forAll { (input: Seq[Int]) =>
        val queue = new RandomizedQueue[Int]
        input foreach queue.enqueue
        for (i <- 0 until input.size) {
          val sampled = queue.sample()
          input should contain(sampled)
        }
      }
    }
    it("should handle random order of operations") {
      forAll { (operations: Seq[Operation]) =>
        val queue = new RandomizedQueue[Int]
        var expectedSize = 0
        for (op <- operations) {
          op match {
            case Enqueue(item) =>
              queue.size() should equal(expectedSize)
              queue.enqueue(item)
              expectedSize = expectedSize + 1
              queue.size() should equal(expectedSize)
            case Dequeue =>
              queue.size() should equal(expectedSize)
              if (expectedSize > 0) {
                queue.dequeue()
                expectedSize = expectedSize - 1
                queue.size() should equal(expectedSize)
              }
          }
        }
      }
    }
    describe("should have correct iterator") {
      it("should return all items") {
        forAll { (input: Seq[Int]) =>
          val queue = new RandomizedQueue[Int]
          input foreach queue.enqueue
          queue.iterator().toSeq should contain theSameElementsAs input
        }
      }
      it("should throw exception when empty and `next()` called") {
        val queue = new RandomizedQueue[Int]
        intercept[NoSuchElementException] {
          queue.iterator().next()
        }
      }
    }
  }
}
