package com.rentalcars.bo;

import lombok.Getter;

@Getter
public class Car {

    private String sipp;
    private String name;
    private Float price;
    private String supplier;
    private Float rating;

    public static class Builder {
        private String sipp;
        private String name;
        private Float price;
        private String supplier;
        private Float rating;

        public Builder() {
        }

        public Car build() {
            return new Car(this);
        }

        public Builder withSipp(String sipp) {
            this.sipp = sipp;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withPrice(Float price) {
            this.price = price;
            return this;
        }

        public Builder withSupplier(String supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder withRating(Float rating) {
            this.rating = rating;
            return this;
        }
    }

    private Car(Builder builder) {
        this.sipp = builder.sipp;
        this.name = builder.name;
        this.price = builder.price;
        this.supplier = builder.supplier;
        this.rating = builder.rating;
    }
}
