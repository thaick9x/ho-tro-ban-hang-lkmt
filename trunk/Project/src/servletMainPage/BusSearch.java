package servletMainPage;

import com.google.gson.Gson;

import bean.HomeMenuBean;
import dao.DaoTTLK;
import dto.Search;

public class BusSearch {
	private DaoTTLK dao = new DaoTTLK();
	
	public HomeMenuBean searchLinhKien(String dk) throws Exception {
		Gson json = new Gson();
		Search search = json.fromJson(dk, Search.class);
		HomeMenuBean kq = new HomeMenuBean();
		
		switch (search.getBy()) {
			case "name": {
				kq = dao.searchName(search.getDk());
				break;
			}
		}
		return kq;
	}
	
}
