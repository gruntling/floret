package com.how2java.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {

	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
		try{
			Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf8","root","");
			
			Statement s=c.createStatement();
			for(int i=1;i<=10;i++){
				String sqlFormat="insert into category values (null, '���Է���%d')";
				String sql=String.format(sqlFormat, i);
				s.executeQuery(sql);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
