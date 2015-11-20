object MainScala extends App {

  def steps(from : Long = 100, to : Long, digits : Array[Int]) : Long = {
    def closestJump: Long = {

      def jump(move : (Long => Long), point: Long = to, distance : Long = 1) : (Long, Long) =
        if (!containsDigits(point)) (point, distance)
        else jump(move, move(point), distance + 1)

      if (!containsDigits(to))
        to
      else {
        def left = (n : Long) => n - 1
        def right = (n : Long) => n + 1
        val (leftPoint, leftDistance) = jump(left)
        val (rightPoint, rightDistance) = jump(right)
        if (leftDistance < rightDistance) leftPoint else rightPoint
      }
    }

    def containsDigits(number: Long) =
      digits.toStream.exists(digit => number.toString.contains(digit.toString))

    def distance(from : Long, to : Long) =
      if (from > to) from - to else to - from

    val stepsWithJump = 1 + distance(closestJump, to)
    val stepsByStep = distance(to, from)

    if (stepsWithJump > stepsByStep) stepsByStep else stepsWithJump
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