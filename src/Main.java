import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {// Написать программу для проверки на валидность введенного ip адреса. Пусть ip адрес задается с консоли.
        // Программа должна проверять валидность введенного ip адреса с помощью регулярного выражения и выводить результат проверки на экран.
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        Pattern pattern = Pattern.compile("([0-2]?[0-5]{1,2}[.]){4}");// полный диапазон IP-адресации – это адреса от 0.0.0.0 до 255.255.255.255.
        Matcher matcher = pattern.matcher(ip);
        System.out.println(matcher.matches());

        //Программа на вход получает путь к файлу (формат тхт). Файл содержит произвольный текст. В этом тексте может быть номер документа(один или несколько), емейл и номер телефона.
        // Документ может содержать не всю информацию, т.е. например, может не содержать номер телефона, или другое.
                FileReader fileReader = new FileReader("D://Task.txt");
        StringBuilder builder = new StringBuilder();
        int c = -1;
        while ((c = fileReader.read()) != -1) {
            builder.append((char) c);
        }
        fileReader.close();
        String text = builder.toString();
        Pattern patternNumberOfDoc = Pattern.compile("([0-9]{4}-){2}[0-9]{2}");// Номер документа в формате: xxxx-xxxx-xx, где x - это любая цифра;
        Pattern patternTelephone = Pattern.compile("[+(][0-9]{2}[)][0-9]{7}");//номер телефона в формате: +XXX(ХХ)ХХХХХХХ.
        Pattern patternEmail = Pattern.compile("[\\w.\\-]@[\\w\\-][.]\\w{2,3}]");
        Matcher matcherNumberOfDoc = patternNumberOfDoc.matcher(text);
        Matcher matcherTelephone = patternTelephone.matcher(text);
        Matcher matcherEmail = patternEmail.matcher(text);
        Map<String, String> dBase = new HashMap<>();// Необходимо извлечь информацию. Извлеченную информацию необходимо сохранить в следующую структуру данных:
        // Map, где ключ типа String – это что сохраняем(email, document number, phone number), значение типа String с информацией.
        while (matcherNumberOfDoc.find()) {
            dBase.put("document number", matcherNumberOfDoc.group());
        }
        while (matcherTelephone.find()) {
            dBase.put("phone number", matcherTelephone.group());
        }
        while (matcherEmail.find()) {
            dBase.put("email", matcherEmail.group());
        }
        System.out.println(dBase.toString());//  В конце вывести все найденные элементы Map.
    }
}

