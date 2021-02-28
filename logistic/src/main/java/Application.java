import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import model.Loads;
import model.Location;

import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        String databaseUrl = "jdbc:sqlite:memory:logistic";
        ConnectionSource con = new JdbcConnectionSource(databaseUrl);

        // instantiate the dao
        Dao<Location, Long> locationDao = DaoManager.createDao(con, Location.class);
        Dao<Loads, Long> loadsDao = DaoManager.createDao(con, Loads.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTable(con, Location.class);
        TableUtils.createTable(con, Loads.class);

        // create an instance of Account
        Location l1 = new Location(null, "testLoc1");
        Location l2 = new Location(null, "testLoc2");
        Location l3 = new Location(null, "testLoc3");

        // persist the account object to the database
        locationDao.create(l1);
        locationDao.create(l2);
        locationDao.create(l3);

        // retrieve the account from the database by its id field (name)
//            Account account2 = loadsDao.queryForId("Jim Coakley");
//            System.out.println("Account: " + account2.getName());

        loadsDao.create(new Loads(null, "l1", 1L));
        loadsDao.create(new Loads(null, "l2", 2L));
        loadsDao.create(new Loads(null, "l3", 3L));

        List<Location> locationList = locationDao.queryForAll();
        System.out.println(locationList);

        Location location1 = locationDao.queryForId(1L);
        System.out.println(location1.toString());
        System.out.println("----");
        System.out.println(loadsDao.queryForId(3L));
        Loads loads1 = loadsDao.queryForId(1L);
        Loads loads2 = loadsDao.queryForId(2L);
        System.out.println(loads1);
        System.out.println(loads2);

        // close the connection source
        con.close();
    }

//    public static void main(String[] args) throws SQLException, IOException {
//
//        dbFactory = new OrmLiteConnectionFactory(":memory:", SqliteDialect.Provider);
//
//        var sqliteRepo = new OrmLiteAuthRepository(dbFactory);
//        sqliteRepo.CreateMissingTables();
//        System.out.println("hi");
//        System.out.println();
//        ConnectionSource con = new JdbcConnectionSource("jdbc:sqlite::memory:");
////            LocationDao locationDao = DaoManager.createDao(con, Location.class);
////            LoadsDao loadsDao = DaoManager.createDao(con, Loads.class);
//        Dao<Location, Long> locationDao = DaoManager.createDao(con, Location.class);
//        Dao<Loads, Long> loadsDao = DaoManager.createDao(con, Loads.class);
//
//        Location l1 = new Location(null, "testLoc1");
//        Location l2 = new Location(null, "testLoc2");
//        Location l3 = new Location(null, "testLoc3");
//
//        locationDao.create(l1);
//        locationDao.create(l2);
//        locationDao.create(l3);
//
//        loadsDao.create(new Loads(null, "l1", l1));
//        loadsDao.create(new Loads(null, "l2", l2));
//        loadsDao.create(new Loads(null, "l3", l3));
//
//        List<Location> locationList = locationDao.queryForAll();
//        System.out.println(locationList);
//
//        Location location1 = locationDao.queryForId(1L);
//        System.out.println(location1.toString());
//        System.out.println("----");
//        System.out.println(loadsDao.queryForId(3L));
//        Loads loads1 = loadsDao.queryForId(1L);
//        Loads loads2 = loadsDao.queryForId(2L);
//        System.out.println(loads1);
//        System.out.println(loads2);
//        con.close();
//    }

    public void checkWorkability() {

    }

//    /**
//     * Не работает совсем.
//     *
//     * @throws JAXBException
//     * @throws FileNotFoundException
//     */
//    public void XMLWriter() throws JAXBException, FileNotFoundException {
//        JAXBContext context = JAXBContext.newInstance(MasterEntity.class);
//        Marshaller marshaller = context.createMarshaller();
//        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//
//        marshaller.getAttachmentMarshaller();
//
//        MasterEntity me = new MasterEntity();
//
//        File file = new File("test.xml");
//        FileOutputStream fos = new FileOutputStream(file);
//        marshaller.marshal(me, fos);
//    }

//    public static void mainMenu() {
//        try {
//            Application app = new Application(new LoadsRepositoryImpl(), new LocationRepositoryImpl());
//            String mainMenu =
//                    "Введите номер или название из списка и нажмите \"Enter\"\n" +
//                            "1. Создание N кол-ва грузов в ячейке\n" +
//                            "2. Вывод общей информации о грузах в ячейках\n" +
//                            "3. Экспорт всех данных в xml файл\n" +
//                            "0. Стоп\n";
//            System.out.println(mainMenu);
//            System.out.printf("Ввод: ");
//
//            Scanner sc = new Scanner(System.in);
//            while (sc.hasNext()) {
//                String in = sc.nextLine();
//                switch (in.toLowerCase(Locale.ROOT)) {
//                    case "0":
//                    case "стоп":
//                        return;
//                    case "1":
//                    case "создание n кол-ва грузов в ячейке":
//                        System.out.printf("\nВведите через пробел количество грузов и название ячейки: ");
//                        String[] strings = sc.nextLine().split(" ");
//                        try {
//                            Integer.parseInt(strings[0]);
//                        } catch (NumberFormatException e) {
//                            mainMenu();
//                        }
//                        app.createLoads(Integer.parseInt(strings[0]), strings[1]);
//                        mainMenu();
//                    case "2":
//                    case "вывод общей информации о грузах в ячейках":
//                        System.out.printf("\nВведите название ячейки: ");
//                        app.getLoadsAmountByLocName(sc.nextLine());
//                        mainMenu();
//                    case "3":
//                    case "экспорт всех данных в xml файл":
//                        app.XMLWriter();
//                        mainMenu();
//                    default:
//                        System.out.println("\nНеверный ввод. Попробуйте еще раз через 3 секунды\n");
//                        Thread.sleep(3000);
//                        mainMenu();
//                }
//            }
//        } catch (JAXBException | FileNotFoundException | InterruptedException e) {
//            e.printStackTrace();
//        }

}
