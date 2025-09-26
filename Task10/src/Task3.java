import java.util.Scanner;

public class Task3 {

    // Дополните объявление поля friendsContactBook, которое будет хранить в себе список номеров телефонов друзей
    private static ContactBook...friendsContactBook =...
            // Напишите объявления полей colleaguesContactBook, classmatesContactBook и relativesContactBook,
            // которые будут хранить списки электронных адресов, соцсетей и почтовых адресов соответственно
            ...


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
class Address ...{
private final String city;
private final String address;

public Address(String name, String city, String address) {
        ...
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
        ...

public void sendMessage() {
    System.out.println("Отправим открытку в город " + city + " по адресу: " + address);
}

        ...

public void print() {
    System.out.println("Город: " + getCity());
    System.out.println("Адрес: " + getAddress());
}

        }


// Дополните объявление класса Contact
public ...

Contact {
    // Класс должен содержать одно полe - имя пользователя name
        ...

    // И два метода - sendMessage() для отправки сообщения и print() для печати информации о контакте
        ...
}


// Ограничьте класс ContactBook так, чтобы он могу хранить в себе только список контактов
class ContactBook ...{
        // Объявите поле класса contacts - список контактов книги
        ...

public void addContact(...contact) {
    contacts.add(contact);
}

public void printList() {
    // Выведите на экран весь список контактов книги
        ...
    System.out.println("Имя: " + contact.getName());
    contact.print();
}

public void congratulate(String name) {
    boolean contactPresented = false; //проверяем есть ли контакт в базе
    // Найдите контакт в книге по имени, и отправьте ему сообщение с помощью метода sendMessage()
        ...
    System.out.println("Поздравим с Новым годом ваш контакт из записной книжки: " + name);
    contact.sendMessage();

    // Если контакт не найден, выведите соответствующее сообщение
    System.out.println("Не найден контакт с указанным именем.");
}

        }


// Унаследуйте класс от базового класса, описывающего контакт Contact
class Email ...{
private final String email;

public Email(String name, String email) {
        ...
    this.email = email;
}

public String getEmail() {
    return email;
}

// Метод sendMessage переопределяет метод базового класса
        ...

public void sendMessage() {
    System.out.println("Отправим новогоднюю картинку коллеге на электронную почту " + email);
}

        ...

public void print() {
    System.out.println("Email: " + getEmail());
}
        }

// Унаследуйте класс от базового класса, описывающего контакт Contact
class Phone ...{
private final String phoneNumber;

public Phone(String name, String phoneNumber) {
        ...
    this.phoneNumber = phoneNumber;
}

public String getPhoneNumber() {
    return phoneNumber;
}

// Метод sendMessage переопределяет метод базового класса
        ...

public void sendMessage() {
    System.out.println("Звоним другу по номеру " + phoneNumber + " и зовем на кофе.");
}

        ...

public void print() {
    System.out.println("Номер телефона: " + getPhoneNumber());
}
        }


// Унаследуйте класс от базового класса, описывающего контакт Contact
class SocialNetworkContact ...{
private final String socialNetwork;
private final String username;

public SocialNetworkContact(String name, String socialNetwork, String username) {
        ...
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
        ...

public void sendMessage() {
    System.out.println("Отправим забавный стикер одногруппнику в соцсети " + socialNetwork + ", имя пользователя " + username);
}

        ...

public void print() {
    System.out.println("Социальная сеть: " + socialNetwork);
    System.out.println("Имя пользователя: " + username);
}
        }