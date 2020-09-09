package raupp.solution1;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class Solution {

    public static Root getJson(int page,int userId) {
        URL url = null;
        try {
            url = new URL("https://jsonmock.hackerrank.com/api/transactions/search?userId="+userId+"&page="+page);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            if(responseCode != 200)
                throw new RuntimeException("HttpResponseCode: " +responseCode);
            else{
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String input;
                StringBuffer response = new StringBuffer();
                while ((input = bfReader.readLine()) != null) {
                    response.append(input);
                }
                bfReader.close();
                Gson gson = new Gson();
                Root root = gson.fromJson(response.toString(), Root.class);
                return root;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static float sumAmount(List<Datum> list,int userId, int locationId, int netStart, int netEnd) {
        float totalAmount = 0;
        for(Datum data : list){
            int ip = Integer.parseInt(data.getIp().substring(0,data.getIp().indexOf(".")));
            String sAmount = data.getAmount().replace("$","");
            sAmount = sAmount.replace(",","");
            float amount = Float.parseFloat(sAmount);
            if(data.getUserId() == userId && data.getLocation().getId() == locationId && ip >= netStart && ip <= netEnd ){
                totalAmount += amount;
            }
        }
        return totalAmount;
    }
    public static int getTransactions(int userId, int locationId, int netStart, int netEnd) {

        Root root = getJson(1,userId);
        float totalAmount = 0;
        totalAmount += sumAmount(root.getData(),userId,locationId,netStart,netEnd);
        for(int i = 2; i<=root.getTotalPages(); i++){
            root = getJson(i,userId);
            totalAmount += sumAmount(root.getData(),userId,locationId,netStart,netEnd);
        }
        return Math.round(totalAmount);
    }
    public static void main(String[] args) {
        System.out.println(getTransactions(4,1,5,50));
    }

}
