package work.aijiu.helloandroid.model;

/**
 * @author aijiu
 * @time 2020-12-17
 * 历史记录实体类
 */
public class HistoryRecord {
    /**
     * id 主键
     */
    private int id;
    /**
     * name 名称
     */
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HistoryRecord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public HistoryRecord() {
    }
}
