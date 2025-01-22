package org.project.helper;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import java.io.FileReader;
import java.io.IOException;

public class DataProv {


        @DataProvider(name = "dataProvider")
        public String[] usersData () throws IOException, ParseException {

            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\loginsData.json");
            Object o = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) o;
            JSONArray jsonArray = (JSONArray) jsonObject.get("usersCredentials");
            String [] usersData = new String[jsonArray.size()];
            for (int i=0; i<jsonArray.size(); i++) {
                JSONObject data = (JSONObject) jsonArray.get(i);
                String userEmail = (String) data.get("email");
                String userPassword = (String) data.get("password");
                usersData[i] = userEmail+", "+userPassword;
            }

            return usersData;
        }
}
