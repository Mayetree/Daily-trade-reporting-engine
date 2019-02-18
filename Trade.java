import java.util.Date;

public class Trade {

	String entity;
	String buyOrSell;
	Double agreedFX;
	String currency;
	Date instructionDate;
	Date originalSettlementDate;
	Date finalSettlementDate;
	int units;
	Double pricePerUnit;

	public Trade(String entity, String buyOrSell, Double agreedFX, String currency, Date instructionDate,
			Date originalSettlementDate, int units, Double pricePerUnit) {
		this.entity = entity;
		this.buyOrSell = buyOrSell;
		this.agreedFX = agreedFX;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.originalSettlementDate = originalSettlementDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getBuyOrSell() {
		return buyOrSell;
	}

	public void setBuyOrSell(String buyOrSell) {
		this.buyOrSell = buyOrSell;
	}

	public Double getAgreedFX() {
		return agreedFX;
	}

	public void setAgreedFX(Double agreedFX) {
		this.agreedFX = agreedFX;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getOriginalSettlementDate() {
		return originalSettlementDate;
	}

	public void setOriginalSettlementDate(Date originalSettlementDate) {
		this.originalSettlementDate = originalSettlementDate;
	}

	public Date getFinalSettlementDate() {
		return finalSettlementDate;
	}

	public void setFinalSettlementDate(Date finalSettlementDate) {
		this.finalSettlementDate = finalSettlementDate;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public Double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

}
