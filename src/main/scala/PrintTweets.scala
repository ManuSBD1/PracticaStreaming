import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.streaming._
import org.apache.spark.streaming.twitter._
import org.apache.spark.streaming.StreamingContext._
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SQLContext
import org.jboss.netty.handler.codec.string.StringDecoder
import org.apache.spark.streaming.kafka._
import Utilities._
import twitter4j.TwitterObjectFactory


object PrintTweets {

  def main(args: Array[String]) {

    val ssc = new StreamingContext("local[*]", "PrintTweets", Seconds(1))

    setupLogging()


    val tops : String="messi"
    val topicMap = tops.split(",").map((_, 1)).toMap
    val stream = KafkaUtils.createStream(ssc, "localhost", "spark", topicMap)

    val rawTweets = stream.map(_._2)

    ssc.checkpoint("C:\\twitter_dump")
    //apartado A: recojo los tweets
    val tweets = rawTweets.map(TwitterObjectFactory.createStatus)
    //apartado B: cuento los tweets en el batch
    val count = tweets.count()
    count.map(i=>"Tweets en el batch: "+i).print()
    //apartado C: cuento los tweets por unidad de tiempo
    val countByWindow = tweets.countByWindow(Seconds(60),Seconds(5))
    countByWindow.map(i=>"Tweets por unidad de tiempo: "+i).print()
    //apartado D: muestro el name y el screen name del usuario
    val users = tweets.map(t=> "Name: "+t.getUser().getName()+" Screen Name: "+t.getUser().getScreenName())
    users.print()


    ssc.start()
    ssc.awaitTermination()
  }
}
