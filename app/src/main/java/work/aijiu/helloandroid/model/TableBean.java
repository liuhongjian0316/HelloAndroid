package work.aijiu.helloandroid.model;

/**
 * @author aijiu
 * @time 2020-12-27
 */
public class TableBean {

    /**
     * 参数
     */
    private String param;

    /**
     * 作用
     */
    private String effect;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    @Override
    public String toString() {
        return "TableBean{" +
                "param='" + param + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }

    public TableBean() {
    }

    public TableBean(String param, String effect) {
        this.param = param;
        this.effect = effect;
    }
}
