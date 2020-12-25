package Model;

public class News {

    public String DATA;
    public String TITLE;
    public String TEXT;

    public News(String DATA, String TITLE, String TEXT) {
        this.DATA = DATA;
        this.TITLE = TITLE;
        this.TEXT = TEXT;
    }

    public String getDATA() {
        return DATA;
    }

    public void setDATA(String DATA) {
        this.DATA = DATA;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTEXT() {
        return TEXT;
    }

    public void setTEXT(String TEXT) {
        this.TEXT = TEXT;
    }
}
