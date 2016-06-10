package com.santhoshn.hltokenize.service;

/**
 * Created by santhosh on 09/06/16.
 */

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by santhosh on 09/06/16.
 */

/**
 * Created by santhosh on 07/06/16.
 */
public class HLCard {
    private static final Pattern AmexRegex = Pattern.compile("^3[47][0-9]{13}$");
    private static final Pattern MasterCardRegex = Pattern.compile("^5[1-5][0-9]{14}$");
    private static final Pattern VisaRegex = Pattern.compile("^4[0-9]{12}(?:[0-9]{3})?$");
    private static final Pattern DinersClubRegex = Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{11}$");
    private static final Pattern RouteClubRegex = Pattern.compile("^(2014|2149)");
    private static final Pattern DiscoverRegex = Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{12}$");
    private static final Pattern JcbRegex = Pattern.compile("^(?:2131|1800|35\\d{3})\\d{11}$");

    private Map<String, Pattern> regexMap;
    private String number;
    private String cvv;
    private Integer expMonth;
    private Integer expYear;

    public HLCard() {
        regexMap = new HashMap<String, Pattern>();
        regexMap.put("Amex", AmexRegex);
        regexMap.put("MasterCard", MasterCardRegex);
        regexMap.put("Visa", VisaRegex);
        regexMap.put("DinersClub", DinersClubRegex);
        regexMap.put("EnRoute", RouteClubRegex);
        regexMap.put("Discover", DiscoverRegex);
        regexMap.put("Jcb", JcbRegex);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getCardType() {
        String cardType = "Unknown";

        try {
            String cardNum = number.replace(" ", "").replace("-", "");
            for (Map.Entry<String, Pattern> kvp : regexMap.entrySet()) {
                if (kvp.getValue().matcher(cardNum).find()) {
                    cardType = kvp.getKey();
                    break;
                }
            }

        } catch (Exception e) {
            cardType = "Unknown";
        }

        return cardType;
    }
}
