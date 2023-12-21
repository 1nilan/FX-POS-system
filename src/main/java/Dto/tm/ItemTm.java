package Dto.tm;import javafx.scene.control.Button;

public class ItemTm {

    private String code;
    private String des;

    private double price;
    private int qty;
    private Button btn;

    public ItemTm(String code, String des, double price, int qty, Button btn) {
        this.code = code;
        this.des = des;
        this.price = price;
        this.qty = qty;
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ItemTm{" +
                "code='" + code + '\'' +
                ", des='" + des + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", btn=" + btn +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
