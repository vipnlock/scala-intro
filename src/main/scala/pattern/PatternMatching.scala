package pattern

import scala.util.Random

object PatternMatching {
  def main(args: Array[String]): Unit = {
    val pm = new PatternMatching

    println("testSimple: " + pm.testSimple)
    println("matchTest: " + pm.matchTest(1))
    println(pm.matchTest(2))
    println(pm.matchTest(3))

    println("Case class Notification:")
    val someSms = SMS("12345", "Are you there?")
    val someVoiceRecording = VoiceRecording("Tom", "voicerecording.org/id/123")
    println(pm.showNotification(someSms))  // prints You got an SMS from 12345! Message: Are you there?
    println(pm.showNotification(someVoiceRecording))  // you received a Voice Recording from Tom! Click the link to hear it: voicerecording.org/id/123

    println("Case class Notification with important component:")
    val importantPeopleInfo = Seq("867-5309", "jenny@gmail.com")

    val someSms2 = SMS("867-5309", "Are you there?")
    val someVoiceRecording2 = VoiceRecording("Tom", "voicerecording.org/id/123")
    val importantEmail = Email("jenny@gmail.com", "Drinks tonight?", "I'm free after 5!")
    val importantSms = SMS("867-5309", "I'm here! Where are you?")
    println(pm.showImportantNotification(someSms2, importantPeopleInfo))
    println(pm.showImportantNotification(someVoiceRecording2, importantPeopleInfo))
    println(pm.showImportantNotification(importantEmail, importantPeopleInfo))
    println(pm.showImportantNotification(importantSms, importantPeopleInfo))

    println("Matching on type only:")
    println(pm.matchingOnTypeOnly(Phone("Samsung")))
    println(pm.matchingOnTypeOnly(Computer("Dell")))
  }
}

class PatternMatching {

  case class Some(x: Int)

  def testSimple: String = {
    val x: Int = Random.nextInt(10)

    x match {
      // alternatives
      case 0 => "zero"
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }
  }

  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  def showNotification(notification: Notification): String = notification match {
    case Email(email, title, _) => s"You got an email from $email with title: $title"
    case SMS(number, message) => s"You got an SMS from $number! Message: $message"
    case VoiceRecording(name, link) => s"you received a Voice Recording from $name! Click the link to hear it: $link"
  }

  def showImportantNotification(notification: Notification, importantPeopleInfo: Seq[String]): String = notification match {
    case Email(email, _, _) if importantPeopleInfo.contains(email) => s"You got an email from special someone $email!"
    case SMS(number, _) if importantPeopleInfo.contains(number) => s"You got an SMS from special someone $number!"
    case other => showNotification(other) // nothing special, delegate to our original showNotification function
  }

  def matchingOnTypeOnly(device: Device) = device match {
    case p: Phone => p.screenOff
    case c: Computer => c.screenSaverOn
  }

  def testAt= {
    val o = Some(2)

    o match {
      case Some(x) => println(x)
//      case None => ???
    }
  }

}
