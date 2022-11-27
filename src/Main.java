import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String ip = scanner.nextLine();
        Pattern pattern = Pattern.compile("([0-2]?[0-9]{1,2}[.]){4}");
        Matcher matcher = pattern.matcher(ip);
        boolean ipCorrect = matcher.matches();
        if (ipCorrect) {
            String[] domain = ip.split(".");

            for (int i = 0; i < domain.length; ++i) {
                if (Integer.parseInt(domain[i]) > 255) {
                    ipCorrect = false;
                    break;
                }
            }
        }

        System.out.println(ipCorrect);
        FileReader fileReader = new FileReader("D://Task.txt");
        StringBuilder builder = new StringBuilder();
        int c = true;

        int c;
        while ((c = fileReader.read()) != -1) {
            builder.append((char) c);
        }

        fileReader.close();
        String text = builder.toString();
        Pattern patternNumberOfDoc = Pattern.compile("([0-9]{4}-){2}[0-9]{2}");
        Pattern patternTelephone = Pattern.compile("[+][0-9]{3}[(][0-9]{2}[)][0-9]{7}");
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


