import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import scala.math._

object FractalLab {

  def main(s: Array[String]) {
    // Пользуемся только стандартным JavaAPI. Никакие процессингы тут не нужны. Учимся все-таки...
    val w = 2048
    val h = w; // квадрат 512x512
    val image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB)
    val g = image.getGraphics
    g.setColor(Color.red)

    var α = 0.0
    var x = 0
    var y = h / 2; // координаты. внимание, α - альфа. сохраняйте исходник в UTF-8.

    def draw(cmd: String, length: Int) {
      println(cmd.length)
      // Используем match
      cmd foreach {
        case 'F' =>
          val x2 = (x + length * cos(α)).toInt
          val y2 = (y + length * sin(α)).toInt
          g.drawLine(x, y, x2, y2)
          x = x2
          y = y2
        case '+' => α += 0.5 * Pi
        case '-' => α -= 0.5 * Pi
      }
    }

    // вспомогательная функция генератор цепочки команд
    def generate(s: String, level: Int, f: String => String): String = {
      if (level > 0)
        generate(f(s), level - 1, f)
      else s
    }

    // функция создающая новые команды, на базе старых
    def next(x: String): String = {
      var result = new StringBuilder()
      // используем match
      x.foreach {
        case 'F' => result ++= "F+F-F-FF+F+F-F"
        case i => result += i
      }
      result.toString
    }
    // рисуем с шагов 2 пикселя цепочку команда 4 поколения.
    draw(generate("F", 9, next), 2)
    // сохраняем в файл
    ImageIO.write(image, "png", new File("koch.png"))
  }
}
