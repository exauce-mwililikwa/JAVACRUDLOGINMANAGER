/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Easy
 */
public class DBConnection {
    private Connection con;
    public Connection connect(){
    
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Success");            
        }
        catch(ClassNotFoundException cnfe){
            System.out.println("Driver not found" + cnfe);
        }        
        String url="jdbc:mysql://localhost:81/wbeducar_java";        
        try{
         con=DriverManager.getConnection(url,"root","");
          System.out.println("Database Connected");
        }
        catch(SQLException se){
          System.out.println("Database Not Found"+se);  
        }
        return con;
        
 
        
        
    }
    public  void getDefendants(String db)  { 
        try{
      Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/wbeducar_java", "root", "");

			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from user");

			XSSFWorkbook workbook = new XSSFWorkbook();

			XSSFSheet spreadsheet = workbook.createSheet("user");
			XSSFRow row = spreadsheet.createRow(1);
			XSSFCell cell;
			cell = row.createCell(0);
			cell.setCellValue("ID");
			cell = row.createCell(1);
			cell.setCellValue("Username");
			cell = row.createCell(2);
			cell.setCellValue("Password");
			cell = row.createCell(3);
			cell.setCellValue("Status");

			int i = 2;

			while (resultSet.next()) {
				row = spreadsheet.createRow(i);
				cell = row.createCell(0);
				cell.setCellValue(resultSet.getInt("ID"));
				cell = row.createCell(1);
				cell.setCellValue(resultSet.getString("Username"));
				cell = row.createCell(2);
				cell.setCellValue(resultSet.getString("Password"));
				cell = row.createCell(3);
				cell.setCellValue(resultSet.getString("Status"));
				i++;
			}

			FileOutputStream out = new FileOutputStream(new File(
					"exceldatabase.xlsx"));
			workbook.write(out);
			out.close();
			System.out.println("File Successfully created");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		

    }
}
}

