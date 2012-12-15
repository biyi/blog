package com.biyi.orm;

import com.biyi.orm.helper.DbHelper;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		//DbHelper.writeAll("bbs", "docstore_dir", "d:\\1\\");
		//DbHelper.writeAll("bbs", "docstore_dir_doc", "d:\\1\\");
		DbHelper.writeAll("blog", "user_log", "/home/biyi/1/");
		//DbHelper.writeAll("bbs", "docstore_doc_product", "d:\\1\\");
	}

}
