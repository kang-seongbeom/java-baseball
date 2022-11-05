package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        try {
            sendMassage(GameMassage.START_MASSAGE);

            List<Integer> computer = randomThreeDigit();
            sendMassage(GameMassage.INPUT_NUMBER);
            List<Integer> user = getUserInput();
            boolean answer = calculationNumber(computer, user);

        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException 발생");
        }

    }

    private static void sendMassage(GameMassage massage){
        System.out.print(massage.getMassage());
    }

    private static void sendMassage(GameMassage massage, int ball, int strike){
        System.out.print(String.format(massage.getMassage(), ball, strike));
    }

    private static boolean calculationNumber(List<Integer> computer, List<Integer> user) {
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < user.size(); i++) {
            if ((int)user.get(i) == (int)computer.get(i)) {
                strike++;
            } else {
                if (computer.contains(user.get(i))) {
                    ball++;
                }
            }
        }

        return calculationResult(strike, ball);
    }

    private static boolean calculationResult(int strike, int ball){
        if (strike == 0 && ball == 0) {
            sendMassage(GameMassage.NOTHING);
        } else {
            if (strike == 3) {
                sendMassage(GameMassage.THREE_STRIKE);
                return true;
            }
            sendMassage(GameMassage.NOT_ANSWER, ball, strike);
        }
        return false;
    }

    private static String removeDuplicated(String str){
        String removeDuplicatedCharRegex = "(([a-z])\\2{1,})";
        return str.replaceAll(removeDuplicatedCharRegex, "");
    }

    private static List<Integer> getUserInput() {
        List<Integer> values = new ArrayList<>();
        String input = Console.readLine();
        input = removeDuplicated(input);
        String regex = "[1-9]{3,3}";

        if (input.matches(regex)) {
            for (int i = 0; i < input.length(); i++) {
                values.add(input.charAt(i) - '0');
            }
            return values;
        }
        throw new IllegalArgumentException();
    }

    private static List<Integer> randomThreeDigit() {
        List<Integer> digits = new ArrayList<>();
        while (digits.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!digits.contains(randomNumber)) {
                digits.add(randomNumber);
            }
        }
        return digits;
    }
}
