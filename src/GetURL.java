
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muhammad Bajwa
 */
public class GetURL {
    static ArrayList<String> AllwebpageForSpecific=new ArrayList<String>();
    static ArrayList<String> Allnearbyzip=new ArrayList<String>();
    static ArrayList<String> AllSpecificurl=new ArrayList<String>();
    static ArrayList<String> BothNearbyAndWithin=new ArrayList<String>();
    static ArrayList<String> Allnearbyurl=new ArrayList<String>();
    static ArrayList<String> Allurl=new ArrayList<String>();
    static ArrayList<String> Allquery=new ArrayList<String>();

    static Connection  connect =null;
    static ResultSet rs1,rs2=null;
    static PreparedStatement pst=null;


    /********************************************/
    public static String getTerms(String s){
        String user="";
        if(s==""){
            JOptionPane.showMessageDialog (null, "You did not enter any key words!" );

        }
        else{
            //      String user=mainSearch.getText();

            user=s.replace(' ','+');
            // System.out.println(user);


        }
        return user;
    }

    /******************************************/

    public static String FormQuery(String url){
        String SpecificURL="";
        String URLPART1=url;
        String URLPART2="";

        try{

            URLPART2=getTerms(Search.mainSearchT);
            SpecificURL=URLPART1+"/search/?query="+URLPART2+"&catAbb=sss";


        }catch(Exception e){

        }

        return SpecificURL;
    }
    /******************************************/





}
