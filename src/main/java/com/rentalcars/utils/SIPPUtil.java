package com.rentalcars.utils;

import com.rentalcars.enums.CarType;
import com.rentalcars.enums.DoorsCarType;
import com.rentalcars.enums.FuelAC;
import com.rentalcars.enums.Transmission;

public class SIPPUtil {

	public static String toString(final char letter, final int position) {
		switch (letter) {
		case 'A':
			return Transmission.A.getValue();
		case 'B':
			return DoorsCarType.B.getValue();
		case 'C':
			if (position == 0)
				return CarType.C.getValue();
			return DoorsCarType.C.getValue();
		case 'D':
			return DoorsCarType.D.getValue();
		case 'E':
			return CarType.E.getValue();
		case 'F':
			if (position == 0)
				return CarType.F.getValue();
			return DoorsCarType.F.getValue();
		case 'I':
			return CarType.I.getValue();
		case 'L':
			return CarType.L.getValue();
		case 'M':
			if (position == 0)
				return CarType.M.getValue();
			return Transmission.M.getValue();
		case 'N':
			return FuelAC.N.getValue();
		case 'P':
			if (position == 0)
				return CarType.P.getValue();
			return DoorsCarType.P.getValue();
		case 'R':
			return FuelAC.R.getValue();
		case 'S':
			return CarType.S.getValue();
		case 'T':
			return DoorsCarType.T.getValue();
		case 'V':
			return DoorsCarType.V.getValue();
		case 'W':
			return DoorsCarType.W.getValue();
		// 'X'
		default:
			return "Special";

		}
	}
}