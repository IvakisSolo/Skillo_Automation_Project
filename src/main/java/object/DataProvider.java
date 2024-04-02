package object;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class DataProvider {

    private static final RandomStringGenerator STRING_GENERATOR = new RandomStringGenerator.Builder().withinRange('a', 'z').build();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String generateRandomUsername() {
        String username = RandomStringUtils.randomAlphanumeric(7);
        char firstChar = Character.toUpperCase(username.charAt(0)); 
        return firstChar + username.substring(1);
    }

    public static String generateRandomEmail() {
        String username = generateRandomUsername();
        return username + "@example.com";
    }

    public static String generateRandomDOB() {
        LocalDate start = LocalDate.of(1970, 1, 1);
        LocalDate end = LocalDate.of(2000, 12, 31);
        LocalDate randomDate = start.plusDays(ThreadLocalRandom.current().nextLong(0, end.toEpochDay() - start.toEpochDay()));
        return randomDate.format(DATE_FORMATTER);
    }

    private static String randomPassword;

    public static String generateRandomPassword() {
        randomPassword = RandomStringUtils.randomAlphanumeric(10);
        char randomUpperCase = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
        char randomDigit = (char) ThreadLocalRandom.current().nextInt('0', '9' + 1);
        int replaceIndex = ThreadLocalRandom.current().nextInt(randomPassword.length());
        randomPassword = randomPassword.substring(0, replaceIndex) + randomUpperCase + randomPassword.substring(replaceIndex + 1);
        randomPassword = randomPassword.substring(0, ThreadLocalRandom.current().nextInt(randomPassword.length())) + randomDigit + randomPassword.substring(ThreadLocalRandom.current().nextInt(randomPassword.length()));
        return randomPassword;
    }

    public static String generateRandomInfo() {
        return STRING_GENERATOR.generate(50);
    }

    public static String generateRandomConfirmPassword() {
        return randomPassword;
    }
}