package com.beam.archtec.utils.network;

import java.util.ArrayList;

//import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.log4j.Logger;

/**
 * VlanAdvance provides opertion on vlan
 * @author bzhao024
 * @created Jan 25, 2013
 *
 */
public class VlanAdvance {

	static Logger logger = Logger.getLogger(VlanAdvance.class.getName());
	
	//private String dn; //id@location
	public int id;
	public String name;
	public String description;
	public IpAdvance ip = new IpAdvance();
	public IpAdvance ipFrom = new IpAdvance();
	public IpAdvance ipTo = new IpAdvance();
	public int mask;
	public String location;
	public boolean isNew;
	
	public ArrayList<String> excludedIPs; //strings in "ipFrom - ipTo" format

	public VlanAdvance() {
	}
	
	public VlanAdvance(String dn) throws Exception {
		setDn(dn);
	}
	
/*	
	public void setDn(int vId, String location) {
		dn = String.format("%d@%s",vId,location);
	}
*/	
	/**
	 * Set dn, id, and location
	 * @param dn	VLAN dn in "id@location" format
	 */
	public void setDn(String dn) throws Exception {
		try {
			String[] val = dn.split("@");
			if (val.length == 2) {
				id = Integer.parseInt(val[0]);
				location = val[1];
			}
			else
				throw new Exception();
		}
		catch (Exception e) {
			throw new Exception("Invalid DN format (" + dn + ")",e);
		}
	}
	
	public String getDn() {
		return String.format("%d@%s",id,location);
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getNote() {
		return description;
	}
	
	public String getNet() throws Exception {
		return ip.addr;
	}
	
	public int getMask() {
		return mask;
	}
	
	public String getIpFrom(){
		return ipFrom.addr;
	}
	
	public String getIpTo(){
		return ipTo.addr;
	}
	
	/**
	 * Return excluded IP ranges ("ipFrom - ipTo")
	 * @return	"|" delimited string
	 */
	public String getExcluded() {
		StringBuilder ips = new StringBuilder();
		if (excludedIPs != null) {
			for (String range: excludedIPs) {
				ips.append("|").append(range);
			}
		}
		return (ips.length()>0 ? ips.substring(1) : "");
	}
	
	public int getMaskBits() {
		int octet = mask;
		int bits = 24; //class C
		for (; octet>0; bits++) {
			octet = (octet << 1) & 255;
		}
		return bits;
	}
	
	private void setIpfrom() throws Exception{
		int a = mask;
		int[] current = IpAdvance.stringToOctets(ip.addr);
		int[] min = new int[4];
		int b;
		for(int i = 0 ; i < 5; i++,a-=8){
			if(a>=8){
				b = 255;
			}else if(a>0){
				b = 255 ^ (255>>a);
			}else{
				b = 0;
			}
			min[i]=b&current[i];
		}
		this.ipFrom.addr = IpAdvance.octetsToString(min);
	}
	private void setIpTo() throws Exception{
		int a = mask;
		int[] current = IpAdvance.stringToOctets(ip.addr);
		int[] max = new int[4];
		int b;
		for(int i = 0 ; i < 5; i++,a-=8){
			if(a>=8){
				b = current[i];
			}else if(a>0){
				b = 255 ^ (255>>a);
				b = b&current[i];
				b = b|(255>>a);
			}else{
				b = 255;
			}
			max[i] = current[i];
		}
		this.ipTo.addr = IpAdvance.octetsToString(max);
	}
}
