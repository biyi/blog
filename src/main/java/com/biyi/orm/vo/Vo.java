package com.biyi.orm.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.biyi.orm.helper.VoHelper;

public class Vo {
	private String voName;
	private String tableName;
	private Collection<Attribute> attributes;
	private String packageName;
	private Attribute primaryKey;
	
	private final Package lang = Package.getPackage("java.lang");
	
	private Set<Class<?>> importClasses = null;
	
	public void setImportClasses(Set<Class<?>> importClasses) {
		this.importClasses = importClasses;
	}

	public Set<Class<?>> getImportClasses(){
		if(importClasses == null){
			if(attributes != null && attributes.size() > 0){
				importClasses = new HashSet<Class<?>>(attributes.size());
				for(Attribute attribute : attributes){
					Class<?> c = attribute.getType();
					if(!c.getPackage().equals(lang))
						importClasses.add(c);
				}
			}
		}
		return importClasses;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		Pattern p = Pattern.compile("(.*)_\\d{3}$");
		Matcher m = p.matcher(tableName);
		if(m.find()){
			tableName = m.group(1);
		}
		this.tableName = tableName;
		this.voName = VoHelper.getClassNameFromTableName(tableName);
	}

	public String getVoName() {
		return voName;
	}

	public Collection<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Collection<Attribute> attributes) {
		this.attributes = attributes;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public Attribute getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Attribute primaryKey) {
		this.primaryKey = primaryKey;
	}
}
