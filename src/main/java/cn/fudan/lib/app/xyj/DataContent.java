package cn.fudan.lib.app.xyj;


import java.util.List;

public class DataContent {

    private String name;
    private List<String> values;

    public DataContent(String name, List<String> values) {
        this.name = name;
        this.values = values;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}
