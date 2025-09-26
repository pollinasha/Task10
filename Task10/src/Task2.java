import java.util.Scanner;

public class Task2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Заполните данные для ипотечной заявки и узнайте статус одобрения");
        System.out.println("Введите ФИО:");
        String name = scanner.nextLine();

        System.out.println("Ваш возраст:");
        byte age = scanner.nextByte();

        System.out.println("Планируемая сумма ипотеки:");
        int mortgageAmount = scanner.nextInt();

        scanner.nextLine();
        System.out.println("Трудоустроены ли вы сейчас? (д/н)");
        String employedString = scanner.nextLine();
        boolean employed = employedString.equalsIgnoreCase("д");

        MortgageRequest mortgageRequest = new MortgageRequest(name, age, mortgageAmount, employed);
        mortgageRequest.validate();

    }

}


// Дополните базовый класс для всех правил валидации
abstract class ValidationRule<T> {
    protected final T value;
    private final String errorMessage;

    protected ValidationRule(T value, String errorMessage) {
        this.value = value;
        this.errorMessage = errorMessage;
    }

    public abstract boolean isValid();

    public String getErrorMessage() {
        return errorMessage;
    }
}


// Дополните класс для проверки возраста пользователя
class AgeValidationRule extends ValidationRule<Byte> {

    public AgeValidationRule(Byte age) {
        super(age, "Возраст для подачи на ипотеку должен быть старше 18 лет");
    }

    @Override
    public boolean isValid() {
        return super.value >= 18;
    }
}


// Дополните класс для проверки трудоустроенности пользователя
class EmploymentValidationRule extends ValidationRule<Boolean>{

    public EmploymentValidationRule(Boolean value) {
        super(value, "Ипотека выдается только трудоустроенным");
    }

    @Override
    public boolean isValid() {
        return super.value;
    }
}


// Дополните класс для проверки суммы ипотеки пользователя
class MortgageAmountValidationRule extends ValidationRule<Integer>{

    public MortgageAmountValidationRule(Integer value) {
        super(value, "Минимальный размер ипотеки - 1.000.000, а максимальный - 10.000.000");
    }

    // Объявите и реализуйте метод для проверки суммы ипотеки ниже
    @Override
    public boolean isValid() {
        if (super.value >= 1000000 && super.value <= 10000000){
            return true;
        }
        return false;
    }
}


class MortgageRequest {

    private final String name;
    private final byte age;
    private final int amount;
    private final boolean employed;

    public MortgageRequest(String name, byte age, int amount, boolean employed) {
        this.name = name;
        this.age = age;
        this.amount = amount;
        this.employed = employed;
    }

    public void validate() {
        System.out.println("Проверка заявки...");

        boolean result = true;

        AgeValidationRule ageValidationRule = new AgeValidationRule(age);
        if (!ageValidationRule.isValid()) {
            result = false;
            System.out.println(ageValidationRule.getErrorMessage());
        }

        MortgageAmountValidationRule amountValidationRule = new MortgageAmountValidationRule(amount);
        if (!amountValidationRule.isValid()) {
            result = false;
            System.out.println(amountValidationRule.getErrorMessage());
        }

        EmploymentValidationRule employmentValidationRule = new EmploymentValidationRule(employed);
        if (!employmentValidationRule.isValid()) {
            result = false;
            System.out.println(employmentValidationRule.getErrorMessage());
        }

        if (result) {
            System.out.println(name + ", вам одобрена заявка на ипотеку!");
        } else {
            System.out.println(name + ", ваша заявка отклонена");
        }
    }
}