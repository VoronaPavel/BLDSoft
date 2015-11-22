object MainScala extends App {

  def steps(from : Long = 100, to : Long, digits : Array[Int]) : Long = {

    def closestJump: Long = {

      def search(move : (Long => Long), point: Long = to, distance : Long = 1) : (Long, Long) =
        if (notContainsDigits(point)) (point, distance)
        else search(move, move(point), distance + 1)

      if (notContainsDigits(to))
        to
      else {
        def left(n : Long) = n - 1
        def right(n : Long) = n + 1
        val (leftPoint, leftDistance) = search(left)
        val (rightPoint, rightDistance) = search(right)
        if (leftDistance < rightDistance) leftPoint else rightPoint
      }
    }

    def notContainsDigits(number: Long) =
      !digits.toStream.exists(digit => number.toString.contains(digit.toString))

    def distance(from : Long, to : Long) =
      if (from > to) from - to else to - from

    val stepsWithJump = 1 + distance(closestJump, to)
    val stepsByStep = distance(to, from)

    if (stepsWithJump > stepsByStep) stepsByStep else stepsWithJump
  }
}