package com.biyi.orm.vo;

import com.biyi.orm.helper.VoHelper;


public class Attribute {
	private Class<?> type;
	private String name;
	private String comment;
	private String colName;

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
		this.name = VoHelper.getAttributeNameFromColName(colName);
	}
	
	public String getSimpleName(){
		return type.getSimpleName();
	}
	
	public String getFormatSimpleName(){
		String simpleName = type.getSimpleName();
		if("Date".equals(simpleName)){
			return "Timestamp";
		}else if("Integer".equals(simpleName)){
			return "Int";
		}
		return simpleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		Class<?> c = VoHelper.getTranslateClass(type);
		if(c != null)
			this.type = c;
		else
			this.type = type;
	}

	public String getName() {
		return name;
	}
	
	public String getFormatName() {
		return Character.toUpperCase(name.charAt(0)) + name.substring(1);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
