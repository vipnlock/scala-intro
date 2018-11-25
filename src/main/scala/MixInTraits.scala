object MixInTraits {
  trait User {
    def username: String
  }

  trait Tweeter {
    // Self-types are a way to declare that a trait must be mixed into another trait
    this: User =>
    // Because we said this: User => in trait Tweeter, now the variable username is in scope for the tweet method.
    def tweet(tweetText: String): Unit = println(s"$username: $tweetText")
  }

  // This also means that since VerifiedTweeter extends Tweeter, it must also mix-in User (using with User).
  class VerifiedTweeter(val username_ : String) extends Tweeter with User {
    def username = s"real $username_"
  }

  def main(args: Array[String]): Unit = {
    val realBeyoncé = new VerifiedTweeter("Beyoncé")
    realBeyoncé.tweet("Just spilled my glass of lemonade")
  }
}
