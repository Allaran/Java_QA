package ru.stqa.pft.sandbox;

public class Main {

    public static void main(String[] args) {

        hello("world");
        hello("user");
        hello("Kirill");

        Square s = new Square(5);
        System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

        Rectangle r = new Rectangle(4, 6);
        System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

        Point p1 = new Point(3, 5);
        Point p2 = new Point(1, 7);

        //Расчёт через метод Main класса
        distance(p1, p2);

        //Расчёт через метод класса Point
        System.out.printf("Расстояние между точками: " + "%.2f", p1.distance(p2));
    }

    public static void hello(String somebody) {
        System.out.println("Hello, " + somebody + "!");
    }

    public static void distance(Point p1, Point p2) {
        double distance = Math.sqrt(Math.pow((p2.x - p1.x), 2) + Math.pow((p2.y - p1.y), 2));
        System.out.printf("Расстояние между точками: " + "%.2f" + "\n", distance);
    }
}
