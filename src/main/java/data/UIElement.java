package data;

public class UIElement {

    private String Keyword;
    //定位方式
    private String by;
    //定位的值信息
    private String value;

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;

    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
