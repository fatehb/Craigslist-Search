/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author Muhammad Bajwa
 */
public class Search extends javax.swing.JFrame {
    static Connection  connect =null;
    static ResultSet rs1,rs2=null;
    static PreparedStatement pst,pst1,pst2=null;
    static ArrayList<String> AllZipInC=new ArrayList<String>();
    static ArrayList<String> zero =new ArrayList<String>();
    static ArrayList<String> ALLWebsites =new ArrayList<String>();
    static ArrayList<String> ALLurls =new ArrayList<String>();
    static ArrayList<String> ALLads =new ArrayList<String>();
    static String mainSearchT="";
    static String zipcodeT="";
    static String radialT="";
    static String query="";
    static int rows;
    static int cols=5;
    static String [][] myArray= new String[100][5];


    public Search()
    {

        connect=connectSQL.ConnectSQL();
        initComponents();


    }


    public void close()
    {

        WindowEvent winClosingEvent =new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);

    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        mainSearch = new javax.swing.JTextField();
        zipcode = new javax.swing.JTextField();
        radial = new javax.swing.JTextField();
        search = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainSearchActionPerformed(evt);
            }
        });

        search.setText(" search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        jLabel1.setText("Key Words:");

        jLabel2.setText("ZIP Code:");

        jLabel4.setText("Radial:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(59, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(mainSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(search)
                                                .addComponent(radial, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(133, 133, 133))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(mainSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(zipcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(radial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(search)
                                .addGap(24, 24, 24))
        );

        setSize(new java.awt.Dimension(400, 322));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        mainSearchT=mainSearch.getText();

        zipcodeT=zipcode.getText();

        radialT=radial.getText();
        try {

            for(int i=0;i<GetZipCodes(zipcodeT,radialT).size();i++){

                ALLurls.add(GetZipCodes(zipcodeT,radialT).get(i));


            }
            removeDuplicates(ALLurls);

            for(int i=0;i<ALLurls.size();i++){
                ALLWebsites.add(GetURL.FormQuery(GetSpecificURL(ALLurls.get(i))));

            }

            for(int i=0;i<ALLWebsites.size();i++){

                try {
                    ALLads.addAll(WebpageReaderWithAgent.GetAllURL(ALLWebsites.get(i), GetSpecificURL(ALLurls.get(i))));

                } catch (Exception ex) {

                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);

                }

            }


            removeDuplicates(ALLads);


            removeTail(ALLads);



            for(int k=0;k<ALLads.size();k++){



                myArray[k][0]=WebpageReaderWithAgent.Getdate(ALLads.get(k));
                myArray[k][1]=WebpageReaderWithAgent.Gettitle(ALLads.get(k));
                myArray[k][2]=WebpageReaderWithAgent.GetBooleanimage(ALLads.get(k));
                myArray[k][3]=WebpageReaderWithAgent.GetBooleanmap(ALLads.get(k));
                myArray[k][4]= ALLads.get(k);

            }
            close();
            ResultsWindow o=new ResultsWindow();
            o.setVisible(true);


        } catch (SQLException ex) {
            System.out.println("error");

        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_searchActionPerformed


    public static ArrayList<String> removeTail(ArrayList<String> s){


        for(int i=s.size()-1;i>99;i--){
            s.remove(i);
            //  System.out.println("remove "+i);
        }

        return s;

    }


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



        if(r.isEmpty()||r==null){


            /********first situation************************/
            /***user did not enter any radial but zip code in craiglist***************/
            /******both local and near by should show up***********************/


            if(rs1.next()&&rs2.next()){

                stringZip1.add(rs1.getString("zip_code"));



                for(int i=0;i<AllZipInCra().size();i++){

                    if((Haversine(zip,AllZipInCra().get(i)))<15){
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

                    if(( Haversine(zip,AllZipInCra().get(i)))<15){//the distance less than 200 belongs to near by
                        stringZip2.add(AllZipInCra().get(i));
                    }

                }

                return stringZip2;

            }
            /*******third situation****************************/
            /********user did not enter radial and zip code is not in craiglist and not in zip***********************/
            /**********no results should show up******************************************/
            else if(!rs1.next()&&!rs2.next()) {



                JOptionPane.showMessageDialog (null, "your zipcode is not in craigslist! no results will show up" );

            }
        }

        else   if(!r.isEmpty()&&r!=null)
        {
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



                JOptionPane.showMessageDialog (null, "your zipcode is not in craigslist! no results will show up!" );


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






    private void mainSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainSearchActionPerformed

    /**
     * @param args the command line arguments
     */




    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        System.out.println(mainSearchT);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField mainSearch;
    private javax.swing.JTextField radial;
    private javax.swing.JButton search;
    private javax.swing.JTextField zipcode;
    // End of variables declaration//GEN-END:variables
}
