package com.hellosign.data;

import com.hellosign.utility.SQLHandler;
import org.testng.annotations.DataProvider;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DataProviders {
    @DataProvider(name = "Different combinations of documents and signatures")
    public static Object[][] completeDocument() {
        return new Object[][]{
                {Data.signingOptionJustMe, Data.pathToPdfFile, Data.drawItIn},
//                {Data.signingOptionJustMe, Data.pathToPdfFile, Data.savedSignatures},
//                {Data.signingOptionJustMe, Data.pathToPdfFile, Data.typeItIn},
//                {Data.signingOptionJustMe, Data.pathToPdfFile, Data.uploadImage},
//                {Data.signingOptionJustMe, Data.pathToPdfFile, Data.useSmartphone},
//                {Data.signingOptionMeAndOthers, Data.pathToPdfFile, ""},
//                {Data.signingOptionJustOthers, Data.pathToPdfFile, ""},
//
//                {Data.signingOptionJustMe, Data.pathToDocFile, Data.savedSignatures},
//                {Data.signingOptionJustMe, Data.pathToDocFile, Data.drawItIn},
//                {Data.signingOptionJustMe, Data.pathToDocFile, Data.typeItIn},
//                {Data.signingOptionJustMe, Data.pathToDocFile, Data.uploadImage},
//                {Data.signingOptionJustMe, Data.pathToDocFile, Data.useSmartphone},
//                {Data.signingOptionMeAndOthers, Data.pathToDocFile, ""},
//                {Data.signingOptionJustOthers, Data.pathToDocFile, ""},
//
//                {Data.signingOptionJustMe, Data.pathToJpegFile, Data.savedSignatures},
//                {Data.signingOptionJustMe, Data.pathToJpegFile, Data.drawItIn},
//                {Data.signingOptionJustMe, Data.pathToJpegFile, Data.typeItIn},
//                {Data.signingOptionJustMe, Data.pathToJpegFile, Data.uploadImage},
//                {Data.signingOptionJustMe, Data.pathToJpegFile, Data.useSmartphone},
//
//
//                {Data.signingOptionMeAndOthers, Data.pathToJpegFile, ""},
//                {Data.signingOptionJustOthers, Data.pathToJpegFile, ""}

        };
    }


    @DataProvider(name = "params")
    public Object[][] getDataFromDataprovider() {
        ArrayList<Object[]> out = new ArrayList<>();
        ResultSet rs = null;

        try {
            SQLHandler.connect("org.postgresql.Driver", "jdbc:postgresql://ip/nameOfDB", "username", "password");

            rs = SQLHandler.stmt.executeQuery(
                    "SELECT account.acct_id, acct_secnd_owner.first_name, acct_secnd_owner.last_name,  acct_secnd_owner.street,acct_secnd_owner.city, acct_secnd_owner.state,acct_secnd_owner.zip, acct_secnd_owner.phone, account.status_info,acct_secnd_owner.email, account.open_date,  account.expire_date FROM account INNER JOIN acct_secnd_owner ON acct_secnd_owner.acct_id = account.acct_id ");
            while (rs.next()) {
                if (rs.getString(1) != null) {

                    String id = rs.getString(1).trim();
                    out.add(new Object[]{id, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)});

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            SQLHandler.disconnect();
        }
        return out.toArray(new Object[out.size()][]);
    }


    @DataProvider(name = "NewRequirementsForPassword")
    public static Object[][] newRequirementsForPassword() {
        return new Object[][]{
                {"Boston1!", true},
                {"Boston2@", true},
                {"#Boston3", true},
                {"$Bos4ton", true},
                {"Bos%5ton", true},
                {"Bos6^ton", true},
                {"New York&7", true},
                {"Miami**12", true},
                {"Miami*-13", true},
                {"Miami*-(14)", true},
                {"Miami*-(13)+", true},
                {"Miami.,90", true},
                {"FLORIDA", false},
                {"***", false},
                {"36363663", false},
                {"nevermind", false},
                {"Fa1234%", false},  // less than 8 characters
                {"Nevermi@", false}, //no digits
                {"Arizona1", false}, // no spec characters
                {"GLORY12*", false}, // no lower case
                {"lowerc#1", false}, // no upper case
                {"Polock12", false}, // no spec charact

        };
    }
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
