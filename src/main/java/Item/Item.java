package Item;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Item implements Comparable<Item> {
    public Long id;
    public double value;
    public double weight;

    @Override
    public int compareTo(Item otherItem) {
        return Double.compare(this.getValue(), otherItem.getValue());
    }

}
