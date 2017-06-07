package com.beam.archtec.test.puzzle.couples;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {

	public static void main(String[] args) throws IOException {
		Person a1 = new Person("a1");
		Person a2 = new Person("a2");
		a1.knownList.add(a2);
		a2.knownList.add(a1);
		Person a3 = new Person("a3");
		Person a4 = new Person("a4");
		a3.knownList.add(a4);
		a4.knownList.add(a3);
		Person a5 = new Person("a5");
		Person a6 = new Person("a6");
		a5.knownList.add(a6);
		a6.knownList.add(a5);
		
		String content = "{\"genre\":[],\"fid\":\"318696\",\"short-title\":\"BANSHEE: SEASON 3 BURTON VS NO\",\"runtime\":\"50000\",\"type\":\"EPISODE\",\"base-id\":\"790131\",\"id\":\"318696\",\"esp\":\"N\",\"aspect-ratio\":\"3\",\"program-asset-format-tkey\":\"PMRS872116\",\"title\":\"BANSHEE: SEASON 3 BURTON VS NOLA CAMERA MOVEMENT\",\"focus-id\":\"790131\",\"runcode\":\"HA\",\"default-fid\":\"318696\",\"vid\":\"277159\",\"program-asset-version-tkey\":\"PMRS841848\",\"version-type\":\"ORIG\",\"origin\":\"SOAP\",\"pid\":\"600918\",\"mpaa\":\"TVMA\",\"program-asset-tkey\":\"PMRS541443\",\"usage-status\":\"USE\",\"cast\":[],\"sound\":\"STER\",\"runcode-type\":\"H\",\"language\":\"ENG\",\"cc\":\"N\",\"prod-seriesId\":\"SERS2681\",\"prod-seriesTkey\":\"PMRS3984\",\"prod-seasonId\":\"SESN2682\",\"prod-seasonTkey\":\"PMRS6946\",\"series\":\"SERS2681\",\"season\":\"SESN2682\",\"episodeNo\":{\"inSeries\":6,\"inSeason\":6},\"numInSeries\":6,\"numInSeason\":6,\"productType\":\"SRS\"}";
		System.out.println(content.length());
		content.getBytes("UTF-8");
		InputStream is = null;
		DataInputStream dataIs = null;
		ByteArrayInputStream bais = null;
		byte[] data = new byte[822];
		try {
			is = new ByteArrayInputStream(content.getBytes("UTF-8"));
			dataIs = new DataInputStream(is);
			dataIs.readFully(data);

			bais = new ByteArrayInputStream(data);
		} catch (Exception e) {
			throw e;
		}finally{
			is.close();
		}
		
		
	}

}
