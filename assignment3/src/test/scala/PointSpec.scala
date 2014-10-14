import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class PointSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("A Point") {
    describe("compareTo()") {
      it("should compare point with different y-coordinates") {
        forAll { (x1: Int, y1: Int, x2: Int, y2: Int) =>
          whenever(y1 < y2) {
            val p1: Point = new Point(x1, y1)
            val p2: Point = new Point(x2, y2)
            p1 compareTo p2 should be < 0
            p2 compareTo p1 should be > 0
          }
        }
      }
      it("should compare point with same y-coordinates") {
        forAll { (x1: Int, x2: Int, y: Int) =>
          val p1: Point = new Point(x1, y)
          val p2: Point = new Point(x2, y)
          whenever(x1 < x2) {
            p1 compareTo p2 should be < 0
            p2 compareTo p1 should be > 0
          }
        }
      }
      it("should compare point with same x and y-coordinates") {
        forAll { (x: Int, y: Int) =>
          val p1: Point = new Point(x, y)
          val p2: Point = new Point(x, y)
          p1 compareTo p2 should equal(0)
        }
      }
    }
  }
}
