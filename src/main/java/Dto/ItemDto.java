package Dto;

public class ItemDto {
    private String code;
    private String des;
    private double price;
    private int qty;

    public ItemDto(String code, String des, double price, int qty) {
        this.code = code;
        this.des = des;
        this.price = price;
        this.qty = qty;
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

    @Override
    public String toString() {
        return "ItemDto{" +
                "code='" + code + '\'' +
                ", des='" + des + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
