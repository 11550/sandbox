import lombok.RequiredArgsConstructor;
import model.Loads;
import model.Location;
import model.MasterEntity;
import repository.impl.LoadsRepositoryImpl;
import repository.impl.LocationRepositoryImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class Application {

    private final LoadsRepositoryImpl loadsRepo;
    private final LocationRepositoryImpl locationRepo;

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        Application app = new Application(new LoadsRepositoryImpl(), new LocationRepositoryImpl());
        app.XMLWriter();

//        app.createLoads(1, "A");
//        app.createLoads(2, "B");
//        app.createLoads(3, "C");

//        List<Loads> loadsList = app.loadsRepo.findAll();
//        System.out.println(loadsList.toString());


//        app.getLoadsAmountByLocName("A B A C");
    }

    private void createLoads(int loadsAmount, String locationName) {
        if (locationRepo.findByName(locationName) == null) {
            locationRepo.insert(new Location(0, locationName));
        }
        int locId = locationRepo.findByName(locationName).getId();
        AtomicInteger count;
        if (loadsRepo.findAll().size() > 0) {
            count = new AtomicInteger(loadsRepo.getMaxId());
        } else {
            count = new AtomicInteger(0);
        }
        for (int i = 0; i < loadsAmount; i++) {
            loadsRepo.insert(new Loads(0, "LD" + count.incrementAndGet(), locId));
        }
    }

    private void getLoadsAmountByLocName(String input) {
        String[] strSpaces = input.split(" ");
        String[] strCommas = input.split(",");
        List<String> stringList = loadsRepo.getLocNames();
        if (strCommas.length > strSpaces.length) {
            for (String s : strCommas) {
                System.out.println(s + "  -  " + getAmountByLocName(stringList, s));
            }
        } else {
            for (String s : strSpaces) {
                System.out.println(s + "  -  " + getAmountByLocName(stringList, s));
            }
        }
    }

    private int getAmountByLocName(List<String> stringList, String locName) {
        List<String> copy = new ArrayList<>(stringList);
        copy.removeAll(Collections.singleton(locName));
        return stringList.size() - copy.size();
    }

    /**
     * Не работает совсем.
     *
     * @throws JAXBException
     * @throws FileNotFoundException
     */
    public void XMLWriter() throws JAXBException, FileNotFoundException {
        JAXBContext context = JAXBContext.newInstance(MasterEntity.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        marshaller.getAttachmentMarshaller();

        MasterEntity me = new MasterEntity();

        File file = new File("test.xml");
        FileOutputStream fos = new FileOutputStream(file);
        marshaller.marshal(me, fos);
    }

    //todo
    public static void mainMenu() {
        try {
            Application app = new Application(new LoadsRepositoryImpl(), new LocationRepositoryImpl());
            String mainMenu =
                    "Введите номер или название из списка и нажмите \"Enter\"\n" +
                            "1. Создание N кол-ва грузов в ячейке\n" +
                            "2. Вывод общей информации о грузах в ячейках\n" +
                            "3. Экспорт всех данных в xml файл\n" +
                            "0. Стоп\n";
            System.out.println(mainMenu);
            System.out.printf("Ввод: ");

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                String in = sc.nextLine();
                switch (in.toLowerCase(Locale.ROOT)) {
                    case "0":
                    case "стоп":
                        return;
                    case "1":
                    case "создание n кол-ва грузов в ячейке":
                        System.out.printf("\nВведите через пробел количество грузов и название ячейки: ");
                        String[] strings = sc.nextLine().split(" ");
                        try {
                            Integer.parseInt(strings[0]);
                        } catch (NumberFormatException e) {
                            mainMenu();
                        }
                        app.createLoads(Integer.parseInt(strings[0]), strings[1]);
                        mainMenu();
                    case "2":
                    case "вывод общей информации о грузах в ячейках":
                        System.out.printf("\nВведите название ячейки: ");
                        app.getLoadsAmountByLocName(sc.nextLine());
                        mainMenu();
                    case "3":
                    case "экспорт всех данных в xml файл":
                        app.XMLWriter();
                        mainMenu();
                    default:
                        System.out.println("\nНеверный ввод. Попробуйте еще раз через 3 секунды\n");
                        Thread.sleep(3000);
                        mainMenu();
                }
            }
        } catch (JAXBException | FileNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
