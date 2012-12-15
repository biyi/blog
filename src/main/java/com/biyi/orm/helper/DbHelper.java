package com.biyi.orm.helper;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.biyi.orm.template1.TemplatePath1;
import com.biyi.orm.vo.Attribute;
import com.biyi.orm.vo.Vo;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class DbHelper {
	
	private static Template idaoTemplate = null;
	private static Template daoTemplate = null;
	private static Template voTemplate = null;
	static{
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(TemplatePath1.class, "");
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			idaoTemplate = cfg.getTemplate("idao.template");
			daoTemplate = cfg.getTemplate("dao.template");
			voTemplate = cfg.getTemplate("bean.template");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeVo(Vo vo, Writer out) throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("vo", vo);
		voTemplate.process(root, out);
	}
	
	public static void writeIDao(Vo vo, Writer out) throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("vo", vo);
		idaoTemplate.process(root, out);
	}
	
	public static void writeDao(Vo vo, Writer out) throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("vo", vo);
		daoTemplate.process(root, out);
	}
	
	public static void writeAll(String dbName, String tableName, String path) throws Exception{
		Vo vo = getDbInfo(dbName, tableName);
		FileWriter writer = new FileWriter(path + "/" + vo.getVoName() + ".java");
		writeVo(vo, writer);
		writer.close();
		
		writer = new FileWriter(path + "/I" + vo.getVoName() + "Dao.java");
		writeIDao(vo, writer);
		writer.close();
		
		writer = new FileWriter(path + "/" + vo.getVoName() + "DaoImpl.java");
		writeDao(vo, writer);
		writer.close();
	}
	
	private static String getPrimaryKeyName(Connection con, String tableName) throws Exception{
		String primaryKeyName = null;
		DatabaseMetaData dbMetaData = con.getMetaData();
		ResultSet rs = dbMetaData.getPrimaryKeys("", "", tableName);
		if(rs.next()){
			primaryKeyName = rs.getString("COLUMN_NAME");
		}
		rs.close();
		return primaryKeyName;
	}
	private static Map<String, Attribute> getAttributes(Connection con, String tableName) throws Exception{
		String sql = "select * from " + tableName + " limit 1";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		ResultSetMetaData metaData = rs.getMetaData();
		int count = metaData.getColumnCount();
		if(count > 0){
			Map<String, Attribute> attributes = new HashMap<String, Attribute>(count);
			for (int i = 1; i <= count; i++) {
				Attribute attribute = new Attribute();
				String colName = metaData.getColumnName(i);
				attribute.setColName(colName);
				Class<?> clazz = Class.forName(metaData.getColumnClassName(i));
				attribute.setType(clazz);
				attributes.put(colName, attribute);
			}
			rs.close();
			return attributes;
		}
		rs.close();
		return null;
	}
	
	public static Vo getDbInfo(String dbName, String tableName){
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbName, "root", "root");
			Vo vo = new Vo();
			vo.setTableName(tableName);
			String primaryKeyName = getPrimaryKeyName(con, tableName);
			Map<String, Attribute> attributes = getAttributes(con, tableName);
			if(primaryKeyName != null && !"".equals(primaryKeyName = primaryKeyName.trim())){
				Attribute primaryKey = attributes.get(primaryKeyName);
				if(primaryKey != null){
					vo.setPrimaryKey(primaryKey);
					attributes.remove(primaryKeyName);
				}
			}
			System.out.println(con.getTypeMap());
			vo.setAttributes(attributes.values());
			return vo;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally{
			if(con != null)
				try {
					con.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
		}
	}
}
