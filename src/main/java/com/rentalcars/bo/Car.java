package com.rentalcars.bo;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.rentalcars.utils.SIPPUtil;

import lombok.Getter;

/*ToDo*/
/*Next Step is to implement Builder Patern here*/

@JsonPropertyOrder({ "sipp", "name", "score", "carType", "spec", "price", "supplier", "rating" })
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true, value = { "carSpec", "carTypeBySIPP" })
@Getter
public class Car {
	private String sipp;
	private String name;
	private String carType;
	private String spec;
	private Float price;
	private String supplier;
	private Integer score;
	private Float rating;
	private Float sumScores;

	public Car(final String name, final Float price) {
		this.name = name;
		this.price = price;
	}

	public Car(final String name, final String carType, final String supplier, final Float rating) {
		this.name = name;
		this.supplier = supplier;
		this.rating = rating;
		this.carType = carType;
	}

	public Car(final String name, final Integer score, final Float rating, final Float sumScores) {
		this.name = name;
		this.score = score;
		this.rating = rating;
		this.sumScores = sumScores;
	}

	public Car(final String sipp, final String name, final Float price, final String supplier, final Float rating) {
		this.sipp = sipp;
		this.name = name;
		this.price = price;
		this.supplier = supplier;
		this.rating = rating;
		if (null != sipp) {
			this.score = computeScore();
		}
		if (score != 0) {
			this.sumScores = this.score + this.rating;
		}
	}

	public Car(final String sipp, final String name, final String carType, final String spec) {
		this.sipp = sipp;
		this.name = name;
		this.carType = carType;
		this.spec = spec;
	}

	private Integer computeScore() {

		int score = hasManualTransmission() ? 1 : 5;
		if (hasAirCon())
			score += 2;

		return score;
	}

	public String getCarTypeBySIPP() {
		if (isNotBlank(sipp)) {
			return SIPPUtil.toString(sipp.charAt(0), 0);
		}
		return null;
	}

	public String getCarSpec() {
		if (isNotBlank(name) && isNotBlank(sipp) && isNotBlank(calculateCarSpec())) {
			return calculateCarSpec();
		}
		return null;
	}

	private String calculateCarSpec() {
		StringBuilder builder = new StringBuilder("");
		if (null != sipp) {
			int length = sipp.length();

			for (int i = 0; i < length; i++) {
				builder.append(SIPPUtil.toString(sipp.charAt(i), i));
				if (i < length - 1)
					builder.append(" - ");
			}
		}
		return builder.toString();
	}

	private boolean hasManualTransmission() {
		return sipp.charAt(2) == 'M';
	}

	private boolean hasAirCon() {
		return sipp.charAt(3) == 'R';
	}

	@Override
	public String toString() {
		return "models.Vehicle{" + "sipp='" + sipp + '\'' + ", name='" + name + '\'' + ", carType='" + carType + '\'' + ", price=" + price + ", supplier='"
				+ supplier + '\'' + ", rating=" + rating + '}';
	}

}
