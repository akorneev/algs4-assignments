import java.util.NoSuchElementException

import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class DequeSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("A Deque") {
    describe("when empty") {
      it("should be really empty") {
        val deque = new Deque[String]
        deque should be('empty)
      }
      it("should have size 0") {
        val deque = new Deque[String]
        deque.size should equal(0)
      }
      it("should throw exception if `removeFirst` is called") {
        val deque = new Deque[String]
        intercept[NoSuchElementException] {
          deque.removeFirst()
        }
      }
      it("should throw exception if `removeLast` is called") {
        val deque = new Deque[String]
        intercept[NoSuchElementException] {
          deque.removeLast()
        }
      }
      it("should become nonempty after inserting element at front") {
        val deque = new Deque[String]
        deque.addFirst("test")
        deque should not be ('empty)
      }
      it("should become nonempty after inserting element at rear") {
        val deque: Deque[String] = new Deque[String]
        deque.addLast("test")
        deque should not be 'empty
      }
      it("should have size 1 after inserting element at front") {
        val deque = new Deque[String]
        deque.addFirst("test")
        deque.size should equal(1)
      }
      it("should have size 1 after inserting element at rear") {
        val deque = new Deque[String]
        deque.addLast("test")
        deque.size should equal(1)
      }
      describe("should have an empty iterator") {
        it("should have no next element") {
          val deque = new Deque[String]
          val it = deque.iterator
          it.hasNext should be(false)
        }
        it("should throw exception when `next()` is called") {
          val deque = new Deque[String]
          val it = deque.iterator
          intercept[NoSuchElementException] {
            it.next()
          }
        }
        it("should throw exception when `remove()` is called") {
          val deque = new Deque[String]
          val it = deque.iterator
          intercept[UnsupportedOperationException] {
            it.remove()
          }
        }
      }
    }
    describe("after adding some elements at front") {
      it("should remove items from the front in the reversed order") {
        forAll { (items: Seq[Int]) =>
          val deque = new Deque[Int]
          items foreach deque.addFirst
          var removed: Seq[Int] = Seq.empty
          try while (true) {
            removed = removed :+ deque.removeFirst()
          } catch {case _: NoSuchElementException =>}
          removed should equal(items.reverse)
        }
      }
      it("should remove items from the rear in the same order") {
        forAll { (items: Seq[Int]) =>
          val deque = new Deque[Int]
          items foreach deque.addFirst
          var removed: Seq[Int] = Seq.empty
          try while (true) {
            removed = removed :+ deque.removeLast()
          } catch {case _: NoSuchElementException =>}
          removed should equal(items)
        }
      }
    }
    describe("after adding some elements at rear") {
      it("should remove items from the rear in the reversed order") {
        forAll { (items: Seq[Int]) =>
          val deque = new Deque[Int]
          items foreach deque.addLast
          var removed: Seq[Int] = Seq.empty
          try while (true) {
            removed = removed :+ deque.removeLast()
          } catch {case _: NoSuchElementException =>}
          removed should equal(items.reverse)
        }
      }
      it("should remove items from the front in the same order") {
        forAll { (items: Seq[Int]) =>
          val deque = new Deque[Int]
          items foreach deque.addLast
          var removed: Seq[Int] = Seq.empty
          try while (true) {
            removed = removed :+ deque.removeFirst()
          } catch {case _: NoSuchElementException =>}
          removed should equal(items)
        }
      }
    }
  }
}
