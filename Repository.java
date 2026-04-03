
package com.mycompany.cashier;

import java.util.List;

public interface Repository<T> {
    void save(List<T> data);
    List load();
}
