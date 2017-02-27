package il.ac.shenkar.hibernate.controller.utils

class Time(var startTime: Long = 0, var endTime: Long = 0) {
  def startCount(): Unit = {
    startTime = System.nanoTime(); // start counter the time
  }
  def computeTime: Double = {
    endTime = System.nanoTime
    val result: Long = endTime - startTime  // compute the time
    var seconds: Double = result.toDouble / 1000000000.0  // convert nano time to seconds
    seconds = (Math.round(seconds * 1000)) /1000.toDouble // leave only 3 digits after dot
    return seconds
  }
}