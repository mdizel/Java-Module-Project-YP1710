
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String dish;
        StringBuilder list = new StringBuilder("Добавленные товары:");
        double dishPrice;
        double dishPriceChek;
        boolean isInputCorrect;
        double total = 0.00;
        double roundTotal= 0.00;
        int i = 0;
        Ruble end = new Ruble();
        People people = new People();
                int num = people.count(); // присваиваем переменной значение возвращаемо методом people.count()
        while (true){
            System.out.println("Введите название блюда или напишите \"завершить\", если ввод закончен.");
            Scanner scanner = new Scanner(System.in);
            dish = scanner.nextLine();
            if (dish.equalsIgnoreCase("завершить")) {
                break;
            }
            System.out.println("Введите цену c копейками."); // Разделитель
            // точка или запятая при вводе, скорее всего, зависит от настроек ОС
            isInputCorrect = scanner.hasNextDouble(); // проверяем что введено число дабл
            if (isInputCorrect) {
                dishPrice = scanner.nextDouble();
                dishPriceChek = Math.round(dishPrice * 100.0) / 100.0;
                if ((dishPrice > 0) && (dishPrice == dishPriceChek)) { // Здесь отсекаем ввод чисел более чем с 2 знаками
                    System.out.println("Товар успешно добавлен.");     // после запятой и нуля
                    total = total + dishPrice;
                    i++;
                    list = new StringBuilder(list + "\n" + i + "." + dish + " - " + dishPrice + " " + end.rubRightEnd(dishPrice)); // Товары собираются в список
                    roundTotal = Math.round(total * 100.0) / 100.0;
                    System.out.println("Общая сумма: " + roundTotal + " " + end.rubRightEnd(total));
                } else {
                    System.out.println("Сегодня скидок нет, все цены в меню кратны копейке, \nвведите реальную стоимость. ");
                }
            } else {
                System.out.println("Введите корректное значение. Внимательней к разделителям точка или запятая.");
            }
        }

        double totalDivide = Math.round(total/num*100.0)/100.0;
        System.out.println(list);
        System.out.println("Общая сумма: " + roundTotal + " " + end.rubRightEnd(total));
        System.out.println("Сумма на каждого " + totalDivide + " "+ end.rubRightEnd(totalDivide));
        kopeika(total, totalDivide, num);
    }
    // метод для определения разницы из за погрешностей округления
    public static void kopeika(double a, double b, int c){
      double roundErr = a - b * (double)c ;
      if (roundErr > 0){
          System.out.println("И " + Math.round(roundErr*100.0) + " коп. за счет заведения");
      } else if (roundErr < 0){
          System.out.println("И " + Math.round(roundErr*100.0) + " коп. на чай");
      }

    }
}
// В этом классе склоняем слово рубль.
// Окончание зависит от последней цифры за исключением 11,12,13,14
class Ruble{
    static int a;

    static String rubEnd;
    final String rubRightEnd (double price) {
        a = (int)price % 100;                 // берем последние две цифры от целочисенной суммы
        if (a > 14){                          //для всех чисел больше 14 берем последнюю цифру
            a = a % 10;
        }
        if (a == 1) {
            rubEnd = "рубль";
        }
        else if((a > 1) && (a < 5)){
            rubEnd = "рубля";
        }
        else if((a >= 5) || (a == 0) ) {
            rubEnd = "рублей";
        }
        return rubEnd;
    }
}
// В этом классе вводим количество человек, отсекаем не правильный ввод - не целые числа,
// буквы, символы и т.п. Также отсекаем числа меньше 2 (1,0, отрицательные)
class People {

    final int count() {
        boolean isInputCorrect;
        int numberOfPeople = 0;
        while (true) {
            System.out.println("На сколько человек нужно разделить счет?");
            Scanner scanner = new Scanner(System.in);
            isInputCorrect = scanner.hasNextInt();
            if (isInputCorrect) {
                numberOfPeople = scanner.nextInt();

                if (numberOfPeople == 0) {
                    System.out.println("Поднимите упавшего гостя из под стола.\nКто то должен оплатить счет.");
                } else if (numberOfPeople < 0) {
                    System.out.println("Кто тогда все это съел?");
                } else if (numberOfPeople == 1) {
                    System.out.println("Тут делить нечего, надо все оплатить самому.");
                }
            } else {
                System.out.println("Ввод некорректен, видать, последняя рюмка была лишней.");
            }
            if (numberOfPeople > 1) {
                return numberOfPeople;

            }
        }


    }
}

