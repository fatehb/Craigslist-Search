
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Bajwa
 */
public class SetResults{
    static ArrayList<String> AllZipInC=new ArrayList<String>();
    static ArrayList<String> zero =new ArrayList<String>();
    static ArrayList<String> ALLWebsites =new ArrayList<String>();
    static ArrayList<String> ALLurls =new ArrayList<String>();
    static Connection  connect =null;
    static ResultSet rs1,rs2=null;
    static PreparedStatement pst,pst1,pst2=null;
    static String [][] table;
    static String mainSearchT="";
    static String zipcodeT="";
    static String radialT="";
    static String query="";



    public static String GetSpecificURL(String zip){

        String URL="";

        try{
            String sql1="select * from final.web where zip_code=?";

            pst=connect.prepareStatement(sql1);
            pst.setString(1, zip );

            rs1=pst.executeQuery();




            if(rs1.next()){

                URL=rs1.getString("url");


            }

            // removeDuplicates(DuplicateURL);

        }catch(Exception e){
            System.out.println("error!");
        }

        return URL;
    }


    public static void removeDuplicates(List list){
        Set uniqueEntries = new HashSet();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (!uniqueEntries.add(element)) // if current element is a duplicate,
                iter.remove();                 // remove it
        }

    }

    public static ArrayList<String> GetZipCodes(String zip , String r ) throws SQLException{
        ArrayList<String> stringZip1=new ArrayList<String>();
        ArrayList<String> stringZip2=new ArrayList<String>();
        ArrayList<String> stringZip3=new ArrayList<String>();
        ArrayList<String> stringZip4=new ArrayList<String>();

        String sql1="select zip_code from final.web where zip_code=?";
        String sql2="select zip_code from final.zip_code where zip_code=?";
        pst1=connect.prepareStatement(sql1);
        pst2=connect.prepareStatement(sql2);

        pst1.setString(1, zip );
        pst2.setString(1,zip);
        rs1=pst1.executeQuery();
        rs2=pst2.executeQuery();



        if(r.isEmpty()){


            /********first situation************************/
            /***user did not enter any radial but zip code in craiglist***************/
            /******both local and near by should show up***********************/


            if(rs1.next()&&rs2.next()){

                stringZip1.add(rs1.getString("zip_code"));



                for(int i=0;i<AllZipInCra().size();i++){

                    if((Haversine(zip,AllZipInCra().get(i)))<60){
                        stringZip1.add(AllZipInCra().get(i));
                    }

                }

                return stringZip1;

            }

            /*******second situation****************************/
            /********user did not enter radial and zip code is not in craiglist but in zip***********************/
            /*only near by should show up**********************************************/
            else if(!rs1.next()&&rs2.next()){




                for(int i=0;i<AllZipInCra().size();i++){

                    if(( Haversine(zip,AllZipInCra().get(i)))<60){//the distance less than 200 belongs to near by
                        stringZip2.add(AllZipInCra().get(i));
                    }

                }

                return stringZip2;

            }
            /*******third situation****************************/
            /********user did not enter radial and zip code is not in craiglist and not in zip***********************/
            /**********no results should show up******************************************/
            else if(!rs1.next()&&!rs2.next()) {



                JOptionPane.showMessageDialog (null, "your zipcode is not in craigslist!" );

            }
        }

        else{
            /*******forth situation****************************/
            /********user entered radial and zip code is  in craiglist ***********************/
         /* both local and nearby should show up and less than radial*/
            if(rs1.next()&&rs2.next()) {

                stringZip3.add(rs1.getString("zip_code"));







                for(int i=0;i<AllZipInCra().size();i++){

                    if(( Haversine(zip,AllZipInCra().get(i)))<Double.parseDouble(r)){
                        // System.out.println( Haversine(zip,AllZipInCra().get(i)));
                        stringZip3.add(AllZipInCra().get(i));
                    }




                }
                return stringZip3;


            }
            /*******fifth situation****************************/
            /********user entered radial and zip code is not in craiglist but in zip***********************/
         /*only nearby should show up and less than radial*/
            else if(!rs1.next()&&rs2.next()) {

                for(int i=0;i<AllZipInCra().size();i++){

                    if(( Haversine(zip,AllZipInCra().get(i)))<Double.parseDouble(r)){//the distance less than 200 belongs to near by
                        stringZip4.add(AllZipInCra().get(i));
                    }

                }


                return stringZip4;
            }
            /*******sixth situation****************************/
            /********user did not enter radial and zip code is not in craiglist and not in zip***********************/
         /*no results should show up**********/
            else if(!rs1.next()&&!rs2.next()) {



                JOptionPane.showMessageDialog (null, "your zipcode is not in craigslist!" );






            }





        }

        return zero ;



    }




    public static ArrayList<String> AllZipInCra(){
        AllZipInC=new ArrayList<String>();
        try{
            String sql1="select zip_code from final.web";

            pst=connect.prepareStatement(sql1);


            rs1=pst.executeQuery();




            while(rs1.next()){

                AllZipInC.add(rs1.getString("zip_code"));


            }


        }catch(Exception e){

        }
        return AllZipInC;

    }






    public static String GetZipUrl(String s){
        String url="";

        try{
            String sql1="select * from final.web where zip_code=?";
            pst=connect.prepareStatement(sql1);
            pst.setString(1, s );

            rs1=pst.executeQuery();

            if(rs1.next()){
                url=rs1.getString("URL");


            }
        }
        catch(Exception e){
            System.out.println("error11");
        }
        return url;


    }



















    /*************************************************************/
    /*************************************************************/
    /*************************************************************/
                    /*the following code are computing distance********/

    private static double deg2rad(double deg) {               /***/
        /***/

        return (deg * Math.PI / 180.0);

    }
    private static double rad2deg(double rad) {

        return (rad * 180.0 / Math.PI);

    }


    public static double Haversine (String s1,String s2) {

        double lat1=getlatitude(s1);
        double lat2=getlatitude(s2);
        double lon1=getLongitude(s1);
        double lon2=getLongitude(s2);




        double theta = lon1 - lon2;

        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);

        dist = rad2deg(dist);

        dist = dist * 60 * 1.1515;



        return dist*1.609344;


    }





    public static double getLongitude(String s){
        double l=0;
        try{
            // connect=connectSQL.ConnectSQL();

            //    String sql1="select * from  database.zip where zip_code=?;";

            String sql1="select web.city,web.state,web.URL,web.zip_code,zip_code.latitude,zip_code.longitude \n"+
                    "from final.web\n"+
                    "join final.zip_code\n"+
                    "on web.zip_code=zip_code.zip_code and web.st=zip_code.state and web.city=zip_code.city where web.zip_code=?;";

            pst=connect.prepareStatement(sql1);
            pst.setString(1, s );

            rs1=pst.executeQuery();

            if(rs1.next()){

                String lon=rs1.getString("longitude");
                l=new Double(lon);
            }
        }catch(Exception e){

        }

        return l;
    }


    public static double getlatitude(String s){
        double a=0;
        try{
            // connect=connectSQL.ConnectSQL();

            // String sql2="select * from  databse.zip where zip_code=?;";

            String sql1="select web.city,web.state,web.URL,web.zip_code,zip_code.latitude,zip_code.longitude \n"+
                    "from final.web\n"+
                    "join final.zip_code\n"+
                    "on web.zip_code=zip_code.zip_code and final.st=zip_code.state and web.city=zip_code.city where web.zip_code=?;";
            pst=connect.prepareStatement(sql1);
            pst.setString(1, s );

            rs2=pst.executeQuery();

            if(rs2.next()){

                String lat=rs2.getString("latitude");
                a=new Double(lat);
            }
        }catch(Exception e){

        }

        return a;
    }

    private static Double toRad(Double value){
        return value*Math.PI/180;
    }


}







