package hackson.test.entity;

/**
 * Created by whh on 2018/9/13.
 */
public class UserInfo {
    private String name;
    private String id;

    public UserInfo(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
