package com.beam.utils.xml;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.testng.annotations.Test;

import com.beam.file.xml.XmlFileHelper;

public class XmlFileHelperTest{
	@Test
	public void dummy() throws DocumentException, ParseException{
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> "
				+ "<FlightAdded xmlns:ns2=\"http://com/swacorp/starr/mx/orderupdateasync/jaxbxml\"> "
				+ "<EventInfo TotalEventCount=\"8\" TopicName=\"SWA.OPNL_SEG.FLT.ADDED\" RequestId=\"147138130\" CreationDatetime=\"2012/04/09 20:31:02 630 GMT\" EventId=\"456515669\" > "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.IATA.ASM.NEW\" /> "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.IATA.FLT.DIV\" /> "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.OPNL_SEG.FLT.CANCELLED\" /> "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.OPNL_SEG.FLT.ADDED\"/> "
				+ "<TopicCounts TopicCount=\"2\" TopicName=\"SWA.OPNL_SEG.FLT.TIME_CHANGED.OPERATIONAL\" /> "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.OPNL_RTE.CHANGED\" /> "
				+ "<TopicCounts TopicCount=\"1\" TopicName=\"SWA.OPNL_SEG.FLT.ARR_CITY_CHANGED\" /> "
				+ "</EventInfo> "
				+ "<AddedFlightLeg FlightLegSequence=\"1\" OperationalRouteIdentifier=\"161\" > "
				+ "<ScheduleChangeOperationType ScChgOpActSeq=\"6\" ScChgOpSeq=\"1\" ScChangeId=\"133754123\" UserName=\"SteveSartin\" ScheduleDate=\"20120409\" ScheduleChangeReasonCode=\"OPERATIONAL\" OperationSubtypeCode=\"REPLACE\" OperationTypeCode=\"DIVERT\" /> "
				+ "<OperationalFlightLegKey WifiEquipt=\"false\" SchdArrStnTzOffset=\"-7\" SchdArrCentralTzOffset=\"-5\" SchdDepStnTzOffset=\"-7\" SchdDepCentralTzOffset=\"-5\" Capacity=\"137\" EquipmentTypeCode=\"733\" NoseNumber=\"N385\" TailNumber=\"N385SW\" ForSale=\"false\" FlightStateCode=\"NORMAL\" FlightTypeCode=\"DIVERT_REPLACE\" ArrivalStationTimeZone=\"America/Los_Angeles\" ScheduledArrivalStationTimeZone=\"America/Los_Angeles\" ScheduledDepartureStationTimeZone=\"America/Los_Angeles\" ArrivalStationCode=\"SJC\" ScheduledArrivalStationCode=\"SJC\" ScheduledDepartureStationCode=\"LAX\" ScheduledArrivalDatetime=\"2012/04/0916:50:00GMT\" ScheduledDepartureDatetime=\"2012/04/0915:20:00GMT\" ScheduleDate=\"20120409\" FlightLegInstanceNumber=\"0\" ScheduledFlightNumber=\"5866\" OperatingCarrier=\"WN\" > "
				+ "<MarketingFlightsMarketing FlightNumber=\"2865\" MarketingCarrier=\"WN\" /> "
				+ "</OperationalFlightLegKey> "
				+ "<ScheduledFlight DestinationStationCode=\"SJC\" OriginationStationCode=\"SAN\" > "
				+ "<ScheduledFlightSegment ArrStnTzOffset=\"-7\" DepStnTzOffset=\"-7\" DepCentralTzOffset=\"-5\" ArrCentralTzOffset=\"-5\" ArrivalStationTimeZone=\"America/Los_Angeles\" DepartureStationTimeZone=\"America/Los_Angeles\" ArrivalDatetime=\"2012/04/0916:50:00GMT\" DepartureDatetime=\"2012/04/0915:20:00GMT\" ArrivalStationCode=\"SJC\" DepartureStationCode=\"SAN\" /> "
				+ "<ScheduledFlightSegment ArrStnTzOffset=\"-7\" DepStnTzOffset=\"-7\" DepCentralTzOffset=\"-5\" ArrCentralTzOffset=\"-5\" ArrivalStationTimeZone=\"America/Los_Angeles\" DepartureStationTimeZone=\"America/Los_Angeles\" ArrivalDatetime=\"2012/04/0916:25:00GMT\" DepartureDatetime=\"2012/04/0915:20:00GMT\" ArrivalStationCode=\"SJC\" DepartureStationCode=\"LAX\" /> "
				+ "</ScheduledFlight> </AddedFlightLeg> "
				+ "</FlightAdded>";
		
		Document doc = XmlFileHelper.getXmlDocument(xml);
		Element el = XmlFileHelper.extractNode(doc, "OperationalFlightLegKey");
		System.out.println(el.attributeValue(""));
		
		SimpleDateFormat sdf = new SimpleDateFormat("y/M/d");
		SimpleDateFormat sdf2 = new SimpleDateFormat("ddMMMyy");
		Date date = sdf.parse("2012/04/09 16:50:00 GMT");
		System.out.println(sdf2.format(date).toUpperCase());
	}
}
