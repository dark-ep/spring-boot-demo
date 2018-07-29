package cn.ep.ws;

import javax.xml.bind.annotation.*;

/**
 * <p>currency的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="currency"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="GBP"/&gt;
 *     &lt;enumeration value="EUR"/&gt;
 *     &lt;enumeration value="PLN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "currency")
@XmlEnum
public enum Currency {

    GBP,
    EUR,
    PLN;

    public static Currency fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }

}
