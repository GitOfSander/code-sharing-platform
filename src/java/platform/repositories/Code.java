package platform.repositories;

public class Code {
    private String date;
    private String code;

    public Code(String date, String code) {
        this.date = date;
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
