package cn.smbms.pojo;

import java.math.BigDecimal;

public class Stock {
    private Integer id;

    private String productname;

    private BigDecimal price;

    private BigDecimal purchaseprice;

    private String productcode;

    private String manufacturer;

    private String place;

    private String inventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPurchaseprice() {
        return purchaseprice;
    }

    public void setPurchaseprice(BigDecimal purchaseprice) {
        this.purchaseprice = purchaseprice;
    }

    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode == null ? null : productcode.trim();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory == null ? null : inventory.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productname=").append(productname);
        sb.append(", price=").append(price);
        sb.append(", purchaseprice=").append(purchaseprice);
        sb.append(", productcode=").append(productcode);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", place=").append(place);
        sb.append(", inventory=").append(inventory);
        sb.append("]");
        return sb.toString();
    }
}