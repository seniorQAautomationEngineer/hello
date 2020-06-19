import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider(name = "Different extensions of documents")
    public static Object[][] testRegistration2() {
        return new Object[][]{

               // {Data.pathToDocFile},
                {Data.pathToPdfFile},
//                {Data.pathToJpegFile},

        };
    }
//    }
//    @DataProvider(name = "Registration2")
//    public static Object[][] testRegistration2() {
//        return new Object[][]{
//
//                {Data.email1, Data.nickname, true},
//             //   {Data.emai2, Data.nickname, false},
//             //   {Data.emai3, Data.nickname, true},
//
//        };
//    }
//
//    @DataProvider(name = "Registration")
//    public static Object[][] testRegistration() throws Exception {
//        ArrayList<Object[]> out = new ArrayList<>();
//        Files.readAllLines(Paths.get("Registration.csv")).stream().forEach(s -> {
//
//            String[] data = s.split(",");
//            out.add(new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]});
//
//        });
//
//        return out.toArray(new Object[out.size()][]);
//    }
//
//
//    @DataProvider(name = "Search")
//    public static Object[][] testSearchFeature() {
//        return new Object[][]{
//
//                      {"18", "80", "Default"},
//                      {"18", "19", "Name"},
//                      {"30", "40", "Views"},
//                      {"60", "80", "Registration date"},
//
//
//        };
//    }
//    @DataProvider(name = "NewRequirementsForPassword")
//    public static Object[][] newRequirementsForPassword() {
//        return new Object[][]{
//                {"Boston1!", true},
//               {"Boston2@", true},
//                {"#Boston3", true},
//                {"$Bos4ton", true},
//                {"Bos%5ton", true},
//                {"Bos6^ton", true},
//                {"New York&7", true},
//                {"Miami**12", true},
//                {"Miami*-13", true},
//                {"Miami*-(14)", true},
//                {"Miami*-(13)+", true},
//                {"Miami.,90", true},
//                {"FLORIDA", false},
//                {"***", false},
//                {"36363663", false},
//                {"nevermind", false},
//                {"Fa1234%", false},  // less than 8 characters
//                {"Nevermi@", false}, //no digits
//                {"Arizona1", false}, // no spec characters
//                {"GLORY12*", false}, // no lower case
//                {"lowerc#1", false}, // no upper case
//                {"Polock12", false}, // no spec charact
//
//        };
//    }
//
//    @DataProvider(name = "OldRequirementsForPassword")
//    public static Object[][] oldRequirementsForPassword() {
//        return new Object[][]{
//
//                {"FLORIDA12", true},
//                {"***", false},
//                {"36363663", false},
//                {"Nevermind34", true},
//                {"Fa12345", false},  // less than 8 characters
//                {"Nevermi@", false}, //no digits
//                {"Arizona1", true},
//                {"GLORY12", false},
//                {"lowerc#1", false}, // no upper case
//                {"Polock12", true}, // no spec charact
//                {"Boston1!", false},
//                {"Boston2@", false},
//                {"Boston2@", false},
//                {"#Boston3", false},
//                {"$Bos4ton", false},
//                {"Bos%5ton", false},
//                {"Bos6^ton", false},
//                {"New York&7", false},
//                {"Miami**12", false},
//                {"Miami*-13", false},
//                {"Miami*-(14)", false},
//                {"Miami*-(13)+", false},
//                {"Miami.,90", false},
//        };
//    }

}
