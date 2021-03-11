package com.xpr.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class FactureGenerator implements IdentifierGenerator {

	
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object)
	        throws HibernateException {

	    String prefix = "FCT";
	    Connection connection = session.connection();

	    try {
	        Statement statement=connection.createStatement();

	        ResultSet rs=statement.executeQuery("select count(name) as name from Factures");
	        
	        Date date = Calendar.getInstance().getTime();  
	        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");   
	        String  dateNow = dateFormat.format(date).toString();
	        if(rs.next())
	        {
	            int id=rs.getInt(1)+10000;
	            String generatedId = prefix +"-" +dateNow +"-"+new Integer(id).toString();
	            return generatedId;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }	

	    return null;
	
	}	

		
}
