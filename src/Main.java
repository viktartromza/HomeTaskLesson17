import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    //Написать программу для проверки на валидность введенного ip адреса. Пусть ip адрес задается с консоли.
    //Программа должна проверять валидность введенного ip адреса с помощью регулярного выражения и выводить результат проверки на экран.
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        Pattern pattern = Pattern.compile("([01]?\\d\\d?.|2[0-4]\\d.|25[0-5].){4}");
        Matcher matcher = pattern.matcher(ip);
        System.out.println(matcher.matches());

//Программа на вход получает путь к файлу (формат тхт). Файл содержит произвольный текст.
// В этом тексте может быть номер документа(один или несколько), емейл и номер телефона.
// Номер документа в формате: xxxx-xxxx-xx, где x - это любая цифра; номер телефона в формате: +(ХХ)ХХХХХХХ.
// Документ может содержать не всю информацию, т.е. например, может не содержать номер телефона, или другое.
// Необходимо извлечь информацию. Извлеченную информацию необходимо сохранить в следующую структуру данных:
// Map, где ключ типа String – это что сохраняем(email, document number, phone number), значение типа String с информацией.
// В конце вывести все найденные элементы Map.
        FileReader fileReader = new FileReader("D://Task.txt");
        StringBuilder builder = new StringBuilder();
        int c;
        while ((c = fileReader.read()) != -1) {
            builder.append((char) c);
        }
        fileReader.close();
        String text = builder.toString();
        Pattern patternNumberOfDoc = Pattern.compile("([0-9]{4}-){2}[0-9]{2}");
        Pattern patternTelephone = Pattern.compile("[+][0-9]{3}[(][0-9]{2}[)][0-9]{7}");// поменял формат на +ХХХ(ХХ)ХХХХХХХ
        Pattern patternEmail = Pattern.compile("\\w+@\\w+[.]\\w{2,3}");
        Matcher matcherNumberOfDoc = patternNumberOfDoc.matcher(text);
        Matcher matcherTelephone = patternTelephone.matcher(text);
        Matcher matcherEmail = patternEmail.matcher(text);
        Map<String, String> dBase = new HashMap();
        for (int n = 1; matcherNumberOfDoc.find(); ++n) {
            dBase.put("document number" + n, matcherNumberOfDoc.group());
        }
        for (int a = 1; matcherTelephone.find(); ++a) {
            dBase.put("phone number" + a, matcherTelephone.group());
        }
        for (int b = 1; matcherEmail.find(); ++b) {
            dBase.put("email" + b, matcherEmail.group());
        }
        System.out.println(dBase.toString());
    }
}


