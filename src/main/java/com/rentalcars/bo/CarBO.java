package com.rentalcars.bo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import lombok.Data;

@JsonPropertyOrder({ "sipp", "name", "score", "carType", "spec", "price", "supplier",
        "rating" })
@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true, value = {
        "carSpec", "carTypeBySIPP" })
@Data
public class CarBO {
    private String sipp;
    private String name;
    private String carType;
    private String spec;
    private Float price;
    private String supplier;
    private Integer score;
    private Float rating;
    private Float sumScores;

    @Override
    public String toString() {
        return "models.Vehicle{" + "sipp='" + sipp + '\'' + ", name='" + name + '\'' + ", carType='" + carType + '\''
                + ", price=" + price + ", supplier='" + supplier + '\'' + ", rating=" + rating + '}';
    }

}
