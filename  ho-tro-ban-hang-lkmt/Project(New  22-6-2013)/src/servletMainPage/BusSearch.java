package servletMainPage;

import bean.HomeMenuBean;
import dao.DaoTTLK;

public class BusSearch {
	private DaoTTLK dao = new DaoTTLK();
	
	public HomeMenuBean searchLinhKien(String dk) throws Exception {
		String[] search = dk.split("!");
		HomeMenuBean kq = new HomeMenuBean();
		
		switch (search[1]) {
			case "name": {
				kq = dao.searchName(search[0]);
				break;
			}
		}
		return kq;
	}
	
}
