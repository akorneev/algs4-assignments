import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSpec, Matchers}

class PointSpec extends FunSpec with Matchers with GeneratorDrivenPropertyChecks {
  describe("A Point") {
    describe("compareTo()") {
      it("should compare point with different y-coordinates") {
        forAll { (x0: Int, y0: Int, x1: Int, y1: Int) =>
          whenever(y0 < y1) {
            val p0 = new Point(x0, y0)
            val p1 = new Point(x1, y1)
            p0 compareTo p1 should be < 0
            p1 compareTo p0 should be > 0
          }
        }
      }
      it("should compare point with same y-coordinates") {
        forAll { (x0: Int, x1: Int, y: Int) =>
          val p0 = new Point(x0, y)
          val p1 = new Point(x1, y)
          whenever(x0 < x1) {
            p0 compareTo p1 should be < 0
            p1 compareTo p0 should be > 0
          }
        }
      }
      it("should compare point with same x and y-coordinates") {
        forAll { (x: Int, y: Int) =>
          val p0 = new Point(x, y)
          val p1 = new Point(x, y)
          p0 compareTo p1 should equal(0)
        }
      }
    }
    describe("slopeTo()") {
      it("should treat the slope of a horizontal line segment as positive zero") {
        forAll { (x0: Int, x1: Int, y: Int) =>
          val p0 = new Point(x0, y)
          val p1 = new Point(x1, y)
          val slope = p0 slopeTo p1
          whenever(x0 != x1) {
            assert((1 / slope).isPosInfinity)
          }
        }
      }
      it("should treat the slope of a vertical line segment as positive infinity") {
        forAll { (x: Int, y0: Int, y1: Int) =>
          val p0 = new Point(x, y0)
          val p1 = new Point(x, y1)
          whenever(y0 != y1) {
            assert((p0 slopeTo p1).isPosInfinity)
          }
        }
      }
      it("should treat the slope of a degenerate line segment (between a point and itself) as negative infinity") {
        forAll { (x: Int, y: Int) =>
          val p0 = new Point(x, y)
          val p1 = new Point(x, y)
          assert((p0 slopeTo p1).isNegInfinity)
        }
      }
      it("should compute slope according to formula (y1 − y0) / (x1 − x0)") {
        forAll { (x0: Int, y0: Int, x1: Int, y1: Int) =>
          whenever(x0 != x1 && y0 != y1) {
            val p0 = new Point(x0, y0)
            val p1 = new Point(x1, y1)
            val expected = (y1 - y0).toDouble / (x1 - x0)
            p0 slopeTo p1 should equal(expected +- 0.1)
          }
        }
      }
    }
  }
}
