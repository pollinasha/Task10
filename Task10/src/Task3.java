import java.util.ArrayList;
import java.util.Scanner;

public class Task3 {

    // Дополните объявление поля friendsContactBook, которое будет хранить в себе список номеров телефонов друзей
    private static ContactBook<Phone> friendsContactBook = new ContactBook<>();
    // Напишите объявления полей colleaguesContactBook, classmatesContactBook и relativesContactBook,
    // которые будут хранить списки электронных адресов, соцсетей и почтовых адресов соответственно
    private static ContactBook<Email> colleaguesContactBook = new ContactBook<>();
    private static ContactBook<SocialNetworkContact> classmatesContactBook = new ContactBook<>();
    private static ContactBook<Address> relativesContactBook = new ContactBook<>();


    public static void main(String[] args) {
        fillBooks();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Сегодня Новый Год! 1 - Отправить поздравление, 0 - Напомнить позднее");
            int mainCommand = scanner.nextInt();
            if (mainCommand == 1) {
                System.out.println("Какую книгу контактов открыть?");
                System.out.println("1 - Друзья, 2 - Коллеги, 3 - Одногруппники, 4 - Родственники");

                int bookIndex = scanner.nextInt();
                if (bookIndex == 1) {
                    friendsContactBook.printList();
                } else if (bookIndex == 2) {
                    colleaguesContactBook.printList();
                } else if (bookIndex == 3) {
                    classmatesContactBook.printList();
                } else if (bookIndex == 4) {
                    relativesContactBook.printList();
                }

                System.out.println("Кого вы хотите поздравить? Введите имя:");
                String name = scanner.next();
                if (bookIndex == 1) {
                    friendsContactBook.congratulate(name);
                } else if (bookIndex == 2) {
                    colleaguesContactBook.congratulate(name);
                } else if (bookIndex == 3) {
                    classmatesContactBook.congratulate(name);
                } else if (bookIndex == 4) {
                    relativesContactBook.congratulate(name);
                }
            } else if (mainCommand == 0) {
                break;
            }
        }
    }

    private static void fillBooks() {
        friendsContactBook.addContact(new Phone("Иван", "+7-909-000-11-22"));
        friendsContactBook.addContact(new Phone("Маша", "+7-999-555-11-22"));
        friendsContactBook.addContact(new Phone("Кирилл", "+7-979-698-00-22"));

        colleaguesContactBook.addContact(new Email("Александр", "sasha@sasha.ru"));
        colleaguesContactBook.addContact(new Email("Павел", "pasha@pasha.ru"));
        colleaguesContactBook.addContact(new Email("Олег", "oleg@oleg.ru"));

        classmatesContactBook.addContact(new SocialNetworkContact("Оля", "НаСвязи", "olya"));
        classmatesContactBook.addContact(new SocialNetworkContact("Женя", "Фотопризма", "zhenya"));

        relativesContactBook.addContact(new Address("Бабуля", "Москва", "Тверская, д.8"));
        relativesContactBook.addContact(new Address("Дедуля", "Воронеж", "Ленина, д.10"));
    }

}

// Унаследуйте класс от базового класса, описывающего контакт Contact
class Address extends Contact{
    private final String city;
    private final String address;

    public Address(String name, String city, String address) {
        super(name);
        this.city = city;
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    // Метод sendMessage переопределяет метод базового класса
    @Override
    public void sendMessage() {
        System.out.println("Отправим открытку в город " + city + " по адресу: " + address);
    }

    @Override
    public void print() {
        System.out.println("Город: " + getCity());
        System.out.println("Адрес: " + getAddress());
    }
}

// Дополните объявление класса Contact
abstract class Contact {
    // Класс должен содержать одно полe - имя пользователя name
    protected final String name;

    Contact(String name) {
        this.name = name;
    }

    // И два метода - sendMessage() для отправки сообщения и print() для печати информации о контакте
    public abstract void sendMessage();
    public abstract void print();

    public String getName() {
        return name;
    }
}

// Ограничьте класс ContactBook так, чтобы он могу хранить в себе только список контактов
class ContactBook<T extends Contact>{
    // Объявите поле класса contacts - список контактов книги
    ArrayList<T> contacts = new ArrayList<>();

    public void addContact(T contact) {
        contacts.add(contact);
    }

    public void printList() {
        // Выведите на экран весь список контактов книги
        for(T contact : contacts) {
            System.out.println("Имя: " + contact.getName());
            contact.print();
        }
    }

    public void congratulate(String name) {
        boolean contactPresented = false; //проверяем есть ли контакт в базе
        // Найдите контакт в книге по имени, и отправьте ему сообщение с помощью метода sendMessage()
        T contact = null;
        for (T item : contacts)
            if (item.getName().equals(name)) {
                contactPresented = true;
                contact = item;
                break;
            }
        if (contact != null) {
            System.out.println("Поздравим с Новым годом ваш контакт из записной книжки: " + name);
            contact.sendMessage();
        }
        else {
            // Если контакт не найден, выведите соответствующее сообщение
            System.out.println("Не найден контакт с указанным именем.");
        }
    }
}

// Унаследуйте класс от базового класса, описывающего контакт Contact
class Email extends Contact{
    private final String email;

    public Email(String name, String email) {
        super(name);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    // Метод sendMessage переопределяет метод базового класса
    @Override
    public void sendMessage() {
        System.out.println("Отправим новогоднюю картинку коллеге на электронную почту " + email);
    }

    @Override
    public void print() {
        System.out.println("Email: " + getEmail());
    }
}

// Унаследуйте класс от базового класса, описывающего контакт Contact
class Phone extends Contact{
    private final String phoneNumber;

    public Phone(String name, String phoneNumber) {
        super(name);
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Метод sendMessage переопределяет метод базового класса
    @Override
    public void sendMessage() {
        System.out.println("Звоним другу по номеру " + phoneNumber + " и зовем на кофе.");
    }

    @Override
    public void print() {
        System.out.println("Номер телефона: " + getPhoneNumber());
    }
}

// Унаследуйте класс от базового класса, описывающего контакт Contact
class SocialNetworkContact extends Contact{
    private final String socialNetwork;
    private final String username;

    public SocialNetworkContact(String name, String socialNetwork, String username) {
        super(name);
        this.socialNetwork = socialNetwork;
        this.username = username;
    }

    public String getSocialNetwork() {
        return socialNetwork;
    }

    public String getUsername() {
        return username;
    }

    // Метод sendMessage переопределяет метод базового класса
    @Override
    public void sendMessage() {
        System.out.println("Отправим забавный стикер одногруппнику в соцсети " + socialNetwork + ", имя пользователя " + username);
    }

    @Override
    public void print() {
        System.out.println("Социальная сеть: " + socialNetwork);
        System.out.println("Имя пользователя: " + username);
    }
}


