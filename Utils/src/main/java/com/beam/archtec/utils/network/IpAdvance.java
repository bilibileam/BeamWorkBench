package com.beam.archtec.utils.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * IpAdvance provides operation on ip
 * @author bzhao024
 * @created Jan 25, 2013
 *
 */
public class IpAdvance implements Comparable<IpAdvance>{

	public String addr = "";
	public String dns; //comma delimited string
	public String ports; //comma delimited string
	
	public int bit1;
	public int bit2;
	public int bit3;
	public int bit4;
	
	public Boolean basicCheck() throws Exception{
		int[] bits = IpAdvance.stringToOctets(addr);
		return bits[3]!=0&&bits[3]!=255;
	}
	
	public void next() throws Exception{
		int[] bits = IpAdvance.stringToOctets(addr);
		bit1 = bits[0];
		bit2 = bits[1];
		bit3 = bits[2];
		bit4 = bits[3];
		do {
			nextRaw();
		} while (bit4==0||bit4==255);
		addr = octetsToString(bit1, bit2, bit3, bit4);
	}
	
	public void previous() throws Exception{
		int[] bits = IpAdvance.stringToOctets(addr);
		bit1 = bits[0];
		bit2 = bits[1];
		bit3 = bits[2];
		bit4 = bits[3];
		do {
			previousRaw();
		} while (bit4==0||bit4==255);
		addr = octetsToString(bit1, bit2, bit3, bit4);
	}
	
	private void previousRaw(){
		bit4--;
		if(bit4<0){
			bit4+=256;
			bit3--;
		}
		if(bit3<0){
			bit3+=256;
			bit2--;
		}
		if(bit2<0){
			bit2+=256;
			bit1--;
		}
	}
	
	private void nextRaw(){
		bit4++;
		bit3 = bit3 + bit4/256;
		bit4 = bit4%256;
		bit2 = bit2 + bit3/256;
		bit3 = bit3%256;
		bit1 = bit1 + bit2/256;
		bit2 = bit2%256;
	}
	
	static public Long getCompareValue(IpAdvance ip){
		Long result = 0L;
		try {
			int[] oct = stringToOctets(ip.addr);
			result = (long)oct[0]*256*256*256 + (long)oct[1]*256*256 + (long)oct[2]*256 + (long)oct[3];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int compareTo(IpAdvance other) {
		return (int) (IpAdvance.getCompareValue(this)-IpAdvance.getCompareValue(other));
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this){
			return true;
		}
		if(obj == null || !(obj instanceof IpAdvance)) {  
			return false;  
		}else {
			IpAdvance other = (IpAdvance)obj;
			return this.addr.equals(other.addr);  
		}
	}

	@Override
	public int hashCode() {
		return this.addr.hashCode();
	}

	/**
	 * Helper method to convert an IP's octets into string
	 * @param octet1	first octet
	 * @param octet2	second octet
	 * @param octet3	third octet
	 * @param octet4	fourth octet
	 * @return	IP address in String format
	 */
	static public String octetsToString(int octet1, int octet2, int octet3, int octet4) {
		return new StringBuilder().append(octet1).append(".").append(octet2).append(".").append(octet3).append(".").append(octet4).toString();
	}

	/**
	 * Helper method to convert an IP's octets into string
	 * @param octets	array of the IP octets
	 * @return	IP address in String format
	 */
	static public String octetsToString(int[] octets) throws Exception {
		if (octets.length != 4)
			throw new Exception("Invalid octets list");
		
		return octetsToString(octets[0],octets[1],octets[2],octets[3]);
	}

	/**
	 * Helper method to convert an IP address into octets array
	 * @param ip	IP address
	 * @return	IP address in octets array
	 */
	static public int[] stringToOctets(String ip) throws Exception {
		String[] octets = toOctets(ip);
		int[] octs = new int[4];
		for (int i=0; i<4; i++)
			octs[i] = Integer.parseInt(octets[i].trim());
		return octs;
	}

	/**
	 * Helper method to retrieve the fourth octet (class C subnet address) of an IP address
	 * @param ip	IP address
	 * @param idx	1 based index of the octets
	 * @return	the octet value
	 * @throws Exception
	 */
	static public int getOctet(String ip, int idx) throws Exception {
		String[] octets = toOctets(ip);
		return Integer.parseInt(octets[idx-1]);
	}
	
	static private String[] toOctets(String ip) throws Exception {
		String[] octets = ip.split("\\.");
		if (octets.length != 4)
			throw new Exception("Invalid IP address format: " + ip);
		
		return octets;
	}
	
	static public boolean ping(String ip) throws Exception {
		try {
			Process p = Runtime.getRuntime().exec("ping "+ip);
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.startsWith("Reply from " + ip)) {
					in.close();
					return true;
				}
			}
			in.close();
			return false;
		}
		catch (IOException e) {
			throw new Exception("IPAddress.ping()",e);
		}
	}
	
}
