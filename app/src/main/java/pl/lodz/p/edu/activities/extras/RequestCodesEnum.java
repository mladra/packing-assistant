package pl.lodz.p.edu.activities.extras;

public enum RequestCodesEnum {

    ITEMS(1);

    private int num;

    RequestCodesEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
