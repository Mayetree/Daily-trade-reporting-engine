import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class ReportServiceImpl {

	private HashMap<String, Double> outgoingUSDRankMap;
	private HashMap<String, Double> incomingUSDRankMap;
	private HashMap<String, Double> outgoingUSDMap;
	private HashMap<String, Double> incomingUSDMap;

	public ReportServiceImpl() {
		this.outgoingUSDRankMap = new HashMap<String, Double>();
		this.incomingUSDRankMap = new HashMap<String, Double>();
		this.outgoingUSDMap = new HashMap<String, Double>();
		this.incomingUSDMap = new HashMap<String, Double>();
	}

	public void settleAllTrades(List<Trade> tradeList) {
		for (Trade trade : tradeList) {
			settleTradeAmount(trade);
		}
	}

	private void settleTradeAmount(Trade trade) {
		settleFinalSettlementDate(trade);
		settleIncomingOutgoingUSD(trade);
		settleIncomingOutgoingEntityRanking(trade);
	}

	private void settleFinalSettlementDate(Trade trade) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(trade.getOriginalSettlementDate());
		String dayName = this.getDayName(cal.get(Calendar.DAY_OF_WEEK));
		if (trade.getCurrency().equals("AED") || trade.getCurrency().equals("SAR")) {
			if (("Friday").equals(dayName)) {
				cal.add(Calendar.DATE, 2);
			} else if (("Saturday").equals(dayName)) {
				cal.add(Calendar.DATE, 1);
			}
		} else {
			if (("Saturday").equals(dayName)) {
				cal.add(Calendar.DATE, 2);
			} else if (("Sunday").equals(dayName)) {
				cal.add(Calendar.DATE, 1);
			}
		}

		trade.setFinalSettlementDate(cal.getTime());
	}

	private void settleIncomingOutgoingUSD(Trade trade) {
		// Outgoing settlement

		DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
		String formattedSettlementDate = null;

		try {
			formattedSettlementDate = dateFormat.format(trade.getFinalSettlementDate());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (trade.getBuyOrSell().equals("B")) {
			String key = formattedSettlementDate;
			if (outgoingUSDMap.containsKey(key)) {
				Double tradeOutgoingAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				outgoingUSDMap.put(key, outgoingUSDMap.get(key) + tradeOutgoingAmount);
			} else {
				Double tradeOutgoingAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				outgoingUSDMap.put(key, tradeOutgoingAmount);
			}
		}

		// Incoming Settlement

		if (trade.getBuyOrSell().equals("S")) {
			String key = formattedSettlementDate;
			if (incomingUSDMap.containsKey(key)) {
				Double tradeIncomingAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				incomingUSDMap.put(key, incomingUSDMap.get(key) + tradeIncomingAmount);
			} else {
				Double tradeIncomingAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				incomingUSDMap.put(key, tradeIncomingAmount);
			}
		}

	}

	private void settleIncomingOutgoingEntityRanking(Trade trade) {

		if (trade.getBuyOrSell().equals("B")) {
			String key = trade.getEntity();
			if (outgoingUSDRankMap.containsKey(key)) {
				Double tradeAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				outgoingUSDRankMap.put(key, outgoingUSDRankMap.get(key) + tradeAmount);
			} else {
				Double tradeAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				outgoingUSDRankMap.put(key, tradeAmount);
			}
		} else if (trade.getBuyOrSell().equals("S")) {
			String key = trade.getEntity();
			if (incomingUSDRankMap.containsKey(key)) {
				Double tradeAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				incomingUSDRankMap.put(key, incomingUSDRankMap.get(key) + tradeAmount);
			} else {
				Double tradeAmount = trade.getPricePerUnit() * trade.getUnits() * trade.getAgreedFX();
				incomingUSDRankMap.put(key, tradeAmount);
			}
		}
	}

	private String getDayName(int dayofWeek) {
		switch (dayofWeek) {
		case 1:
			return "Sunday";
		case 2:
			return "Monday";
		case 3:
			return "Tuesday";
		case 4:
			return "Wednesday";
		case 5:
			return "Thursday";
		case 6:
			return "Friday";
		case 7:
			return "Saturday";
		default:
			return null;
		}
	}
	
	public List<String> getOutgoingUSDRankReport() {
		return getUSDRankReport(outgoingUSDRankMap);
	}
	
	public List<String> getIncomingUSDRankReport() {
		return getUSDRankReport(incomingUSDRankMap);
	}
	
	private List<String> getUSDRankReport(HashMap<String, Double> usdRankMap) {
		List<String> usdRankReport = new ArrayList<>();
		int rank = 1;
		int rankSkip = 0;
		int finalRank = 1;
		Double previousAmount = null;
		Set<Entry<String, Double>> rankSet = usdRankMap.entrySet();
		ArrayList<Entry<String, Double>> rankList = new ArrayList<Entry<String, Double>>(rankSet);
		Collections.sort(rankList, new RankComparator());
		for (Map.Entry<String, Double> entry : rankList) {
			if (!entry.getValue().equals(previousAmount)) {
				finalRank = rank + rankSkip;				
				rank++;
			} else {
				rankSkip++;
			}
			usdRankReport.add("Rank : " + finalRank + " Entity : " + entry.getKey() + " Amount : "
					+ entry.getValue());
			previousAmount = entry.getValue();
		}
		return usdRankReport;
	}

	public HashMap<String, Double> getOutgoingUSDRankMap() {
		return outgoingUSDRankMap;
	}

	public HashMap<String, Double> getIncomingUSDRankMap() {
		return incomingUSDRankMap;
	}

	public HashMap<String, Double> getOutgoingUSDMap() {
		return outgoingUSDMap;
	}

	public HashMap<String, Double> getIncomingUSDMap() {
		return incomingUSDMap;
	}

}
