package pl.lodz.p.edu.activities.extras;

public enum ResultCodesEnum {

    OK(0);

    private int num;

    ResultCodesEnum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
