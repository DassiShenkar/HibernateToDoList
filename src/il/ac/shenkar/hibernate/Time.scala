package il.ac.shenkar.hibernate

/**
  * Created by Arel on 20/02/2017.
  */
class Time(var startTime: Long = 0, var endTime: Long = 0) {
  def startCount(): Unit = {
    startTime = System.nanoTime();
  }
  def computeTime: Double = {
    endTime = System.nanoTime
    val result: Long = endTime - startTime
    var seconds: Double = result.toDouble / 1000000000.0
    seconds = (Math.round(seconds * 1000)) /1000.toDouble // leave only 3 digits after dot
    return seconds
}


}