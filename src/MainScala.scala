object MainScala extends App {

  def steps(from : Long = 100, to: Long, excludedDigits : Array[Int]) : Long = {
    def closestJump: Long = {

      def jumpLeft(point: Long = to - 1, distance : Long = 1) : (Long, Long) =
        if (! containsExcluded(point)) (point, distance)
        else jumpLeft(point - 1, distance + 1)

      def jumpRight(point: Long = to + 1, distance : Long = 1) : (Long, Long) =
        if (! containsExcluded(point)) (point, distance)
        else jumpRight(point + 1, distance + 1)

      if (! containsExcluded(to))
        to
      else {
        val (leftPoint, leftDistance) = jumpLeft(to - 1)
        val (rightPoint, rightDistance) = jumpRight(to + 1)
        if (leftDistance < rightDistance)
          leftPoint
        else
          rightPoint
      }
    }

    def containsExcluded(number: Long) =
      excludedDigits.toStream.exists(e => number.toString.contains(e.toString))

    def distance(from : Long, to : Long) =
      if (from > to) from - to else to - from

    if (1 + distance(closestJump, to) > distance(to, from))
      distance(to, from)
    else
      1 + distance(closestJump, to)
  }

  val excluded1: Array[Int] = Array(1)
  val excluded2: Array[Int] = Array(9)
  val excluded3: Array[Int] = Array(9, 8)
  val excluded4: Array[Int] = Array(9, 8, 7, 6, 5, 4, 3, 2)

  val solve1 = steps(0, 10, excluded1)
  val solve2 = steps(100, 95, excluded2)
  val solve3 = steps(100, 90, excluded3)
  val solve4 = steps(100, 90, excluded4)

  println(2 == solve1)
  println(5 == solve2)
  println(10 == solve3)
  println(10 == solve4)

}