package object;

import org.apache.commons.lang3.RandomStringUtils;
import java.util.concurrent.ThreadLocalRandom;

public class DataProvider {

    public static String generateRandomUsername() {
        String username = RandomStringUtils.randomAlphanumeric(7);
        char firstChar = Character.toUpperCase(username.charAt(0));
        return firstChar + username.substring(1);
    }

    public static String generateRandomEmail() {
        String username = generateRandomUsername();
        return username + "@example.com";
    }

    private static String randomPassword;

    public static String generateRandomPassword() {
        randomPassword = RandomStringUtils.randomAlphanumeric(10);
        char randomUpperCase = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
        char randomDigit = (char) ThreadLocalRandom.current().nextInt('0', '9' + 1);
        int replaceIndex = ThreadLocalRandom.current().nextInt(randomPassword.length());
        randomPassword = randomPassword.substring(0, replaceIndex) + randomUpperCase + randomPassword.substring(replaceIndex + 1);
        randomPassword = randomPassword.substring(0, ThreadLocalRandom.current().
                nextInt(randomPassword.length())) + randomDigit + randomPassword.substring(ThreadLocalRandom.current().nextInt(randomPassword.length()));
        return randomPassword;
    }

    public static String generateRandomConfirmPassword() {
        return randomPassword;
    }
}