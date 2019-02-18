import java.util.Comparator;
import java.util.Map;

public class RankComparator implements Comparator<Map.Entry<String, Double>>{

	@Override
	public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
		return (o2.getValue()).compareTo( o1.getValue() );
	}

}
