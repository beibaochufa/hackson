package hackson.entity;

/**
 * Created by whh on 2018/9/14.
 */
public class BodyText {
    private String touser;//为空向全部发送
    private String toparty;
    private String totag;
    private String msgtype = "text";
    private String agentid;
    private Text text;
    private int safe;

    public BodyText(String touser, String toparty, String totag, String agentid, String text) {
        this.touser = touser;
        this.toparty = toparty;
        this.totag = totag;
        this.agentid = agentid;
        this.text = new Text(text);
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getTotag() {
        return totag;
    }

    public void setTotag(String totag) {
        this.totag = totag;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public Text getText() {
        return text;
    }

    public void setText(Text text) {
        this.text = text;
    }

    public int getSafe() {
        return safe;
    }

    public void setSafe(int safe) {
        this.safe = safe;
    }

    class Text {
        private String content;

        Text(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
