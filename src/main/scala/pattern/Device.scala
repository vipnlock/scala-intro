package pattern

abstract class Device

case class Phone(model: String) extends Device {
  def screenOff = "Turning screen off, on " + model
}

case class Computer(model: String) extends Device {
  def screenSaverOn = "Turning screen saver on... on " + model
}
