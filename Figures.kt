package Figures
// Интерфейсы
interface Movable {
    fun move(dx: Int, dy: Int)
}

interface Transforming {
    fun resize(zoom: Int)

    fun rotate(direction: RotateDirection, centerX: Int, centerY: Int)
}

enum class RotateDirection {
    Clockwise, CounterClockwise
}

// Абстрактный класс Figure
abstract class Figure(val id: Int) {
    abstract fun area(): Float
}

// Классы фигур
class Circle(var x: Int, var y: Int, var radius: Int) : Figure(0), Transforming {
    override fun area(): Float {
        return (Math.PI * radius * radius).toFloat();
    }

    override fun resize(zoom: Int) {
        radius *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        // Поворот круга не имеет смысла, т.к. он симметричен
    }

    fun printVertices() {
        println("Вершины круга:")
        println("Центр: ($x, $y)")
    }
}

class Rect(var x: Int, var y: Int, var width: Int, var height: Int) : Movable, Figure(0), Transforming {
    var color: Int = -1
    lateinit var name: String
    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height)

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    override fun area(): Float {
        return (width*height).toFloat()
    }

    override fun resize(zoom: Int) {
        width *= zoom
        height *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        if (direction == RotateDirection.Clockwise) {
            val temp = width
            width = height
            height = temp
        } else {
            val temp = width
            width = height
            height = temp
        }
    }

    fun printVertices() {
        println("Вершины прямоугольника:")
        println("Верхний левый угол: ($x, $y)")
        println("Верхний правый угол: (${x + width}, $y)")
        println("Нижний правый угол: (${x + width}, ${y + height})")
        println("Нижний левый угол: ($x, ${y + height})")
    }
}

class Square(var x: Int, var y: Int, var side: Int) : Figure(0), Transforming {
    override fun area(): Float {
        return (side * side).toFloat()
    }

    override fun resize(zoom: Int) {
        side *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        // Поворот квадрата не имеет смысла, т.к. он симметричен
    }

    fun printVertices() {
        println("Вершины квадрата:")
        println("Верхний левый угол: ($x, $y)")
        println("Верхний правый угол: (${x + side}, $y)")
        println("Нижний правый угол: (${x + side}, ${y + side})")
        println("Нижний левый угол: ($x, ${y + side})")
    }
}


fun main() {
    val f: Figure = Rect(0,0,1,1)
    val f2: Figure = Circle(0, 0, 1)

    println(f.area())
    println(f2.area())

    val rect = Rect(0, 0, 10, 5)
    val square = Square(0, 0, 5)
    val circle = Circle(0, 0, 5)

    // Перемещение
    rect.move(10, 10)
    println("Прямоугольник после перемещения: x = ${rect.x}, y = ${rect.y}")

    // Масштабирование
    rect.resize(2)
    println("Прямоугольник после масштабирования: ширина = ${rect.width}, высота = ${rect.height}")
    square.resize(2)
    println("Квадрат после масштабирования: сторона = ${square.side}")
    circle.resize(2)
    println("Круг после масштабирования: радиус = ${circle.radius}")

    // Поворот
    println("Прямоугольник до поворота:")
    rect.printVertices()
    rect.rotate(RotateDirection.Clockwise, 5, 5)
    println("Прямоугольник после поворота:")
    rect.printVertices()

    println("Квадрат до поворота:")
    square.printVertices()
    square.rotate(RotateDirection.Clockwise, 5, 5)
    println("Квадрат после поворота:")
    square.printVertices()

    println("Круг до поворота:")
    circle.printVertices()
    circle.rotate(RotateDirection.Clockwise, 5, 5)
    println("Круг после поворота:")
    circle.printVertices()
}