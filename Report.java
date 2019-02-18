import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Report {

	public static void main(String[] args) {
		
		ReportServiceImpl reportController = new ReportServiceImpl();
		
		Trade trade1 = new Trade("zee", "B", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("13 FEB 2019"), 30, 100.0);
		Trade trade2 = new Trade("blueberry", "B", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("16 FEB 2019"), 20, 100.0);
		Trade trade3 = new Trade("samsung", "B", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("17 FEB 2019"), 30, 100.0);
		Trade trade4 = new Trade("ericson", "S", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("13 FEB 2019"), 10, 100.0);
		Trade trade5 = new Trade("apple", "S", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("16 FEB 2019"), 20, 200.0);
		Trade trade6 = new Trade("sony", "S", 1.0, "SGP", changeStringToDate("12 FEB 2019"),
				changeStringToDate("17 FEB 2019"), 10, 100.0);
		Trade trade7 = new Trade("Max", "B", 1.0, "AED", changeStringToDate("12 FEB 2019"),
				changeStringToDate("13 FEB 2019"), 30, 100.0);
		Trade trade8 = new Trade("turbo", "B", 1.0, "SAR", changeStringToDate("12 FEB 2019"),
				changeStringToDate("15 FEB 2019"), 20, 100.0);
		Trade trade9 = new Trade("classic", "B", 1.0, "AED", changeStringToDate("12 FEB 2019"),
				changeStringToDate("16 FEB 2019"), 30, 100.0);
		Trade trade10 = new Trade("tata", "S", 1.0, "AED", changeStringToDate("12 FEB 2019"),
				changeStringToDate("13 FEB 2019"), 30, 100.0);
		Trade trade11 = new Trade("xiaomi", "S", 1.0, "SAR", changeStringToDate("12 FEB 2019"),
				changeStringToDate("15 FEB 2019"), 20, 100.0);
		Trade trade12 = new Trade("nokia", "S", 1.0, "AED", changeStringToDate("12 FEB 2019"),
				changeStringToDate("16 FEB 2019"), 30, 100.0);

		List<Trade> tradeList = new ArrayList<>();		
		tradeList.add(trade1);
		tradeList.add(trade2);
		tradeList.add(trade3);
		tradeList.add(trade4);
		tradeList.add(trade5);
		tradeList.add(trade6);
		tradeList.add(trade7);
		tradeList.add(trade8);
		tradeList.add(trade9);
		tradeList.add(trade10);
		tradeList.add(trade11);
		tradeList.add(trade12);
		
		reportController.settleAllTrades(tradeList);
		
		HashMap<String,Double> outgoingUSDMap = reportController.getOutgoingUSDMap();
		System.out.println("Outgoing USD settlement\n");
		printUSDSettlementReport(outgoingUSDMap);
		
		HashMap<String,Double> incomingUSDMap = reportController.getIncomingUSDMap();
		System.out.println("Incoming USD settlement\n");
		printUSDSettlementReport(incomingUSDMap);
		
		List<String> outgoingUSDRankList = reportController.getOutgoingUSDRankReport();
		System.out.println("Outgoing USD Ranking of entities\n");
		printUSDRankReport(outgoingUSDRankList);

		List<String> incomingUSDRankList = reportController.getIncomingUSDRankReport();
		System.out.println("Incoming USD Ranking of entities\n");
		printUSDRankReport(incomingUSDRankList);
		
	}
	
	private static void printUSDRankReport(List<String>  usdRankReportList) {
		for(String usdRankReport : usdRankReportList ) {
			System.out.println(usdRankReport);
		}
		System.out.println("\n*********************************************************\n");
	}

	private static void printUSDSettlementReport(HashMap<String, Double> usdMap) {
		for (String key : usdMap.keySet()) {
			System.out.println("Date : " + key + " Amount : " + usdMap.get(key));
		}

		System.out.println("\n*********************************************************");
	}
	
	private static Date changeStringToDate(String dateString) {
		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		Date date = new Date();
		try {
			date = dateFormat.parse(dateString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
}
