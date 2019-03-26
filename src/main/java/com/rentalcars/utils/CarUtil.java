package com.rentalcars.utils;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CarUtil {

    public static Integer computeScore(String sipp) {

        int score = hasManualTransmission(sipp) ? 1 : 5;
        if (hasAirCon(sipp))
            score += 2;

        return score;
    }

    public static Float computeSumScore(Integer score, Float rating) {
        float sumScores = 0.0f;
        if (score != 0) {
            sumScores = score + rating;
        }
        return sumScores;
    }

    public static String getCarTypeBySIPP(String sipp) {
        if (isNotBlank(sipp)) {
            return SIPPUtil.toString(sipp.charAt(0), 0);
        }
        return null;
    }

    public static String getCarSpec(String sipp, String name) {
        if (isNotBlank(name) && isNotBlank(sipp) && isNotBlank(calculateCarSpec(sipp))) {
            return calculateCarSpec(sipp);
        }
        return null;
    }

    private static String calculateCarSpec(String sipp) {
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

    private static boolean hasManualTransmission(String sipp) {
        return sipp.charAt(2) == 'M';
    }

    private static boolean hasAirCon(String sipp) {
        return sipp.charAt(3) == 'R';
    }
}
