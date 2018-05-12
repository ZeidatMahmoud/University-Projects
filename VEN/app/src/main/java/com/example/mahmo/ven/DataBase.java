package com.example.mahmo.ven;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.method.QwertyKeyListener;

/**
 * Created by mahmo on 12/28/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    protected   static final String DATABASE_NAME = "qwe.db" ;
    protected static final String TABLE_NAME = "LOGIN";
    private  static final String col3 = "ID" ;
    private  static final String col1 ="NAME" ;
    private  static final String col2 ="PASSWORD" ;
    ///////////////////////////////////////////////////
    protected static final String CUSTOMER_TABLE  ="CUSTOMER";
    private static final String customerName = "CNAME" ;
    private static final String address  ="CADDRESS" ;
    private static final String phone = "CPHONE" ;
    private static final String customerQuery = "CREATE TABLE "+ CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ customerName +" TEXT, "+ address +" TEXT, "+ phone +" TEXT);";
    /////////////////////////////////////////////////////
    protected final static String PROUDUCT_TABLE = "PRODUCTS" ;
    private final static String productName = "PNAME" ;
    private final static String productPrice = "PPRICE" ;
    private final static String productQuntity = "PQUNTITY";
    private final static String ProductQuery = "CREATE TABLE "+ PROUDUCT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ productName+" TEXT, "+ productPrice+" TEXT, "+ productQuntity +" TEXT);";

    ///////////////////////////////////////////////
    protected final static String ORDER_TABLE = "ORDERS" ;
    private final static String customerId = "CUSTOMERID" ;
    private final static String productID  ="PRODUCTID" ;
    private final static String orderQuntity = "ORDERQ" ;
    private final static String orderDate = "ORDERDATE" ;
    private final static String orderDue  ="ORDERDUE" ;
    private final static String orderopaied = "PA" ;
    private final static String orderQuery = "CREATE TABLE "+ ORDER_TABLE+ " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ customerId+" TEXT, "+ productID+" TEXT, "+ orderQuntity +" TEXT, "+ orderDate+" TEXT, "+ orderDue +" TEXT, "+orderopaied+" TEXT);";
    /////////////////////////////////////////
    protected final static String SELECT_TABLE = "SELECTED" ;
    private final static String first = "FIRST" ;
    private final static String selectQuery = "CREATE TABLE "+ SELECT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ first +" TEXT);";
    //////////////////////////////////////
    protected final static String SELECT_ID = "ID_SELECTTION" ;
    private final static String second = "second" ;
    private final static String id_query = "CREATE TABLE "+ SELECT_ID + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ second+" TEXT);";
   ///////////////////////////////////////////////
    protected final static String payment_Table = "PAYMENT" ;
    private final static String paymentCustomer  ="PAYMENTCUSTOMER";
    private final static String totalmoney = "MONEY";
    private final static String paymentDate  ="PAYMENTDATE" ;
    private final static String paymentQuery  = "CREATE TABLE "+ payment_Table + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ paymentCustomer +" TEXT, "+ totalmoney +" TEXT, "+ paymentDate +" TEXT);";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String query = "CREATE TABLE "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , "+ col1 +" TEXT, "+ col2 +" TEXT);";
        db.execSQL(query);
        db.execSQL(customerQuery);
        db.execSQL(ProductQuery);
        db.execSQL(orderQuery);
        db.execSQL(selectQuery);
        db.execSQL(id_query);
        db.execSQL(paymentQuery);

       // String cQuery  ="CREATE TABLE "+ CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,"+ customerName +" TEXT,"+ address +" TEXT,"+phone+" TEXT);";


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String Sql = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        String Sql2 ="DROP TABLE IF EXISTS " + CUSTOMER_TABLE ;
        String ProductSQL = "DROP TABLE IF EXISTS "+PROUDUCT_TABLE ;
        String orderSQL = "DROP TABLE IF EXISTS " + ORDER_TABLE ;
        String selectSQL = "DROP TABLE IF EXISTS " +SELECT_TABLE ;
        String selectIDSQL = "DROP TABLE IF EXISTS " +SELECT_ID ;
        String paymentSQL  ="DROP TABLE IF EXISTS "+payment_Table ;


        db.execSQL(Sql);
        db.execSQL(Sql2);
        db.execSQL(ProductSQL);
        db.execSQL(orderSQL);
        db.execSQL(selectSQL);
        db.execSQL(selectIDSQL);
        db.execSQL(paymentSQL);

        onCreate(db);

    }
    public boolean insertData(String name ,String password){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,name);
        contentValues.put(col2,password);
        long result =db.insertOrThrow(TABLE_NAME,"",contentValues);
        if(result == -1){
            return false;
        }
        return true ;


    }
    public boolean insert(Ven v){
        SQLiteDatabase db = this.getWritableDatabase() ; // write in the database
        ContentValues contentValues = new ContentValues() ; // declare content value


       long inserted=0 ;
        if(v instanceof Customer){

            contentValues.put(customerName,((Customer) v).getName());
            contentValues.put(address,((Customer) v).getAddress());
            contentValues.put(phone,((Customer) v).getPhone());
            inserted = db.insertOrThrow(CUSTOMER_TABLE,"",contentValues);
        }
        if(v instanceof Product){

            contentValues.put(productName,((Product) v).getName());
            contentValues.put(productPrice,""+((Product) v).getPrice());
            contentValues.put(productQuntity,""+((Product) v).getQuntity());
            inserted = db.insertOrThrow(PROUDUCT_TABLE,"",contentValues);

        }
        if(v instanceof  Order){

            contentValues.put(customerId,""+((Order) v).getCustomerid());
            contentValues.put(productID,""+ ((Order) v).getId());
            contentValues.put(orderQuntity,""+ ((Order) v).getQuntity());
            contentValues.put(orderDate,""+((Order) v).getOrderDate());
            contentValues.put(orderDue,""+((Order) v).getDueDate());
            contentValues.put(orderopaied,""+((Order) v).isPaied());
            inserted= db.insertOrThrow(ORDER_TABLE,"",contentValues);

        }
        if(v instanceof Payment){
            contentValues.put(paymentCustomer,""+((Payment) v).getCustomerid());
            contentValues.put(totalmoney,""+((Payment) v).getAmountPaied());
            contentValues.put(paymentDate,""+((Payment) v).getDate());
            inserted =db.insertOrThrow(payment_Table,"",contentValues);
        }
        if(inserted == -1){
            return false;
        }
        return true ;



    }
    public Cursor getAllData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor res =db.rawQuery("select * from "+TABLE_NAME,null);
        return res ;
    }
    public int getRawsCount(String TABLE_NAME) {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }
    public void freeTable(String TABLE_NAME){
        SQLiteDatabase db =this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME ;
        db.execSQL(query);
    }
    public String getUserName(){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT "+col1+" FROM "+TABLE_NAME  ;
       Cursor cursor =  db.rawQuery(query,null) ;
        cursor.moveToLast();
        String userName = cursor.getString(0);
        return userName ;
    }
    public String getPassword(){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT "+col2+" FROM "+TABLE_NAME  ;
        Cursor cursor =  db.rawQuery(query,null) ;
        cursor.moveToFirst() ;
        String userName = cursor.getString(0);
        return userName ;
    }
    public Cursor getListContent(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+customerName+" FROM " + CUSTOMER_TABLE ;
        Cursor cursor = db.rawQuery(query,null) ;
        return cursor ;

    }
    public Cursor getAll(String CUSTOMER_TABLE){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+CUSTOMER_TABLE ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor ;
    }
    public boolean updateQuntity(int Quntity,String name){
        int inserted ;
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(productName,name);
        contentValues.put(productQuntity,Quntity);
        inserted = db.update(PROUDUCT_TABLE,contentValues,productName+" = ?",new String[] { name});
        if(inserted == -1){
            return false ;
        }
        return  true ;
    }
    public void insertSelected(){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();
        contentValues.put(first ,"first");
        db.insertOrThrow(SELECT_TABLE,"",contentValues);

    }
    public void updateSelected(String str,String id){
        SQLiteDatabase db  = this.getWritableDatabase() ;
        ContentValues contentValues =new ContentValues() ;
        contentValues.put("ID",""+id);
        contentValues.put(first,str);
        db.update(SELECT_TABLE,contentValues,"ID = ?",new String[]{""+id});


    }

    public Cursor getSelectedData(){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT * FROM "+SELECT_TABLE;
        Cursor cursor = db.rawQuery(query,null);
        return cursor ;

    }
    public int getProductID(String name ){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT ID FROM "+PROUDUCT_TABLE+" WHERE "+productName+" = "+name ;
        Cursor cursor =db.rawQuery(query,null);
        int id =Integer.parseInt(cursor.getString(0));
        return id ;
    }
    public int getCustommerId(String str){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT ID FROM "+CUSTOMER_TABLE +" WHERE "+customerName+" = "+str ;
        Cursor cursor = db.rawQuery(query,null) ;
        int id = Integer.parseInt(cursor.getString(0));
        return id ;

    }
    public void updateOrders(String id,String quntity ,String date ,String dueDate){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put("ID",id);
        contentValues.put(orderQuntity,quntity);
        contentValues.put(orderDate,date);
        contentValues.put(orderDue ,dueDate);
        db.update(ORDER_TABLE,contentValues,"ID = ?",new String[]{ id });


    }
    public void Delete(){

    }
    public Cursor getProductQuntity(){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT * FROM "+PROUDUCT_TABLE ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor ;
    }
    public Cursor findProductName(){
        SQLiteDatabase db = this .getReadableDatabase() ;
        String query = "SELECT * FROM "+PROUDUCT_TABLE  ;
        Cursor cursor = db.rawQuery(query,null);
        return cursor ;
    }







    /*

    public DataBase(Context context) {
        super(context,"VEN",null ,1);
        SQLiteDatabase db  = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String ="CREATE TABLE login (username varchar(20) PRIMARY KEY , password varchar(20))" ;
        db.execSQL(SQL_String);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
    public void signUp(String username ,String password){
        try {
            String quary = "INSERT INTO login values(?,?)";
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(quary, new String[]{username, password});
            db.close();
        }
        catch (Exception e){}
    }
    public boolean logIn(String username ,String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "SELECT password form login where username = ?";
            Cursor c = db.rawQuery(query, new String[]{username});
            if (c != null) {
                c.moveToFirst();
            }
            if (c.getCount() > 0) {
                String pass = c.getString(0);
                if (pass.equals(password    )) {
                    return true;
                }
            }
        }catch (Exception e){

        }
        return false ;
    }
    */

}
