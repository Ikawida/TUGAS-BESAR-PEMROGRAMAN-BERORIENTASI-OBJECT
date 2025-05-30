package model;

import java.util.ArrayList;
import java.util.List;

public class Transaksi {
    private List<ItemTransaksi> items;
    private int bayar;

    public Transaksi() {
        this.items = new ArrayList<>();
    }

    public void tambahItem(ItemTransaksi item) {
        items.add(item);
    }

    public List<ItemTransaksi> getItems() {
        return items;
    }

    public int getBayar() {
        return bayar;
    }

    public void setBayar(int bayar) {
        this.bayar = bayar;
    }
}
