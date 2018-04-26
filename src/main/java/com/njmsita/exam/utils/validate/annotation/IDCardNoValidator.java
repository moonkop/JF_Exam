package com.njmsita.exam.utils.validate.annotation;

import com.njmsita.exam.utils.format.StringUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class IDCardNoValidator implements ConstraintValidator<IDCardNoValifatorAnnocation,String>
{

    private  String cityCode[] = { "11", "12", "13", "14", "15", "21",
            "22", "23", "31", "32", "33", "34", "35", "36", "37", "41", "42",
            "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",
            "63", "64", "65", "71", "81", "82", "91" };

    /**
     * 每位加权因子
     */
    private  int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
            8, 4, 2 };

    /**
     * 验证所有的身份证的合法性
     *
     * @param idcard 身份证
     * @return 合法返回true，否则返回false
     */
    public  boolean isValidatedAllIdcard(String idcard) {
        if (idcard == null || "".equals(idcard)) {
            return false;
        }
        if (idcard.length() == 15) {
            return validate15IDCard(idcard);
        }
        return validate18Idcard(idcard);
    }

    public  boolean validate18Idcard(String idcard) {
        if (idcard == null) {
            return false;
        }

        // 非18位为假
        if (idcard.length() != 18) {
            return false;
        }
        // 获取前17位
        String idcard17 = idcard.substring(0, 17);

        // 前17位全部为数字
        if (!isDigital(idcard17)) {
            return false;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return false;
        }

        // 校验出生日期
        String birthday = idcard.substring(6, 14);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {// 出生年月日不正确
                return false;
            }

        } catch (ParseException e1) {

            return false;
        }

        // 获取第18位
        String idcard18Code = idcard.substring(17, 18);

        char c[] = idcard17.toCharArray();

        int bit[] = converCharToInt(c);

        int sum17 = 0;

        sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        String checkCode = getCheckCodeBySum(sum17);
        if (null == checkCode) {
            return false;
        }
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        if (!idcard18Code.equalsIgnoreCase(checkCode)) {
            return false;
        }

        return true;
    }

    /**
     * 校验15位身份证
     *
     * <pre>
     * 只校验省份和出生年月日
     * </pre>
     *
     * @param idcard
     * @return
     */
    public  boolean validate15IDCard(String idcard) {
        if (idcard == null) {
            return false;
        }
        // 非15位为假
        if (idcard.length() != 15) {
            return false;
        }

        // 15全部为数字
        if (!isDigital(idcard)) {
            return false;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return false;
        }

        String birthday = idcard.substring(6, 12);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        try {
            Date birthDate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthDate);
            if (!tmpDate.equals(birthday)) {// 身份证日期错误
                return false;
            }

        } catch (ParseException e1) {

            return false;
        }

        return true;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     */
    public  String convertIdcarBy15bit(String idcard) {
        if (idcard == null) {
            return null;
        }

        // 非15位身份证
        if (idcard.length() != 15) {
            return null;
        }

        // 15全部为数字
        if (!isDigital(idcard)) {
            return null;
        }

        String provinceid = idcard.substring(0, 2);
        // 校验省份
        if (!checkProvinceid(provinceid)) {
            return null;
        }

        String birthday = idcard.substring(6, 12);

        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        Date birthdate = null;
        try {
            birthdate = sdf.parse(birthday);
            String tmpDate = sdf.format(birthdate);
            if (!tmpDate.equals(birthday)) {// 身份证日期错误
                return null;
            }

        } catch (ParseException e1) {
            return null;
        }

        Calendar cday = Calendar.getInstance();
        cday.setTime(birthdate);
        String year = String.valueOf(cday.get(Calendar.YEAR));

        String idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

        char c[] = idcard17.toCharArray();
        String checkCode = "";

        // 将字符数组转为整型数组
        int bit[] = converCharToInt(c);

        int sum17 = 0;
        sum17 = getPowerSum(bit);

        // 获取和值与11取模得到余数进行校验码
        checkCode = getCheckCodeBySum(sum17);

        // 获取不到校验位
        if (null == checkCode) {
            return null;
        }
        // 将前17位与第18位校验码拼接
        idcard17 += checkCode;
        return idcard17;
    }

    /**
     * 校验省份
     *
     * @param provinceid
     * @return 合法返回TRUE，否则返回FALSE
     */
    private  boolean checkProvinceid(String provinceid) {
        for (String id : cityCode) {
            if (id.equals(provinceid)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    private  boolean isDigital(String str) {
        return str.matches("^[0-9]*$");
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private  int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private  String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private  int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }


    private int len15;
    private int len18;
    public void initialize(IDCardNoValifatorAnnocation idCardNoValifatorAnnocation)
    {
        this.len15=idCardNoValifatorAnnocation.lenth15();
        this.len18=idCardNoValifatorAnnocation.lenth18();
    }

    public boolean isValid(String number, ConstraintValidatorContext constraintValidatorContext)
    {
        if(StringUtil.isEmpty(number)){
            return true;
        }
        if (number.length()==len15 || number.length()==len18 ){
            return isValidatedAllIdcard(number);
        }
        //禁用默认的message的值
        constraintValidatorContext.disableDefaultConstraintViolation();
        //重新添加错误提示语句
        constraintValidatorContext
                .buildConstraintViolationWithTemplate("请输入15位或18位身份证号码").addConstraintViolation();
        return false;
    }
}