package data;

import java.util.List;

public class Page {
    private String keyWord;//关键字
    private List<UIElement> elements;   //list集合page下的所有页面元素对象

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<UIElement> getElements() {

        return elements;
    }

    public void setElements(List<UIElement> elements) {
        this.elements = elements;
    }

}
