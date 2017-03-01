package il.ac.shenkar.hibernate.controller.utils

/**
  * This class compute time elapsed between 2 times
  * @author Arel Gindos
  * @author Dassi Rosen
  * @author Lior Lerner
  * @param startTime - Sets the start time for count the time elapsed
  * @param endTime - Sets the end of the for count the time elapsed
  */
class Time(var startTime: Long = 0, var endTime: Long = 0) {
  def startCount(): Unit = {
    startTime = System.nanoTime(); // start counter
  }

  /**
    * Returns the time elapsed
    * @return time elapsed in seconds
    */
  def computeTime: Double = {
    endTime = System.nanoTime
    val result: Long = endTime - startTime  // compute the time
    var seconds: Double = result.toDouble / 1000000000.0  // convert nano time to seconds
    seconds = (Math.round(seconds * 1000)) /1000.toDouble // leave only 3 digits after dot
    return seconds
  }
}