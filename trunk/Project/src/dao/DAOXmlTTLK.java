package dao;

import java.io.File;
import java.sql.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import dto.ExtraInfo;


import java.util.LinkedList;

public class DAOXmlTTLK extends DAO {
	private String sqlStatement;
	private PreparedStatement statement;
	
	//Tìm tới file xml chứa thông tin kỹ thuật của loại linh kiện
	private Document xmlFile(String loaiLK) throws Exception {
		String xmlPath = "";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();	
		
		sqlStatement = "select XmlTTKT from loai_lk where loai_LK = ?";
		statement = getConn().prepareStatement(sqlStatement);
		statement.setString(1, loaiLK);			
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			xmlPath = result.getString(1);
		}
		File xmlFile = new File(DAOXmlTTLK.class.getResource(xmlPath).toURI());
		Document doc = dBuilder.parse(xmlFile);
		return doc;
	}
	
	public String getImgUrl(String loaiLK, String maTenLK) throws Exception {
		String imgUrl = "";		
		Document doc = xmlFile(loaiLK);
		doc.getDocumentElement().normalize();	
		NodeList nList = doc.getElementsByTagName(loaiLK);
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			Element eElement = (Element)nNode;
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			if (eElement.getAttribute("maTenLK").equals(maTenLK)) {
				imgUrl = eElement.getElementsByTagName("img").item(0).getTextContent();
			}
			}
		}
		return imgUrl;
	}
	
	public LinkedList<ExtraInfo> getExtraInfo(String loaiLK, String maTenLK) throws Exception {
		LinkedList<ExtraInfo> infos = new LinkedList<>();

		Document doc = xmlFile(loaiLK);
		doc.getDocumentElement().normalize();	
		NodeList nList = doc.getElementsByTagName(loaiLK);

		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);

			Element eElement = (Element)nNode;
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				
				if (eElement.getAttribute("maTenLK").equals(maTenLK)) {
					NodeList list = eElement.getChildNodes();

					for (int j = 0; j < list.getLength(); j++) {
						Node n = list.item(j);
						if (n.getNodeType() == Node.ELEMENT_NODE) {
							ExtraInfo info = new ExtraInfo();
							info.setValue(n.getTextContent());
							info.setName(n.getNodeName());
							infos.add(info);
						}
					}
				}
			}
		}
		return infos;
	}
}
