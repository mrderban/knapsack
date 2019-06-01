package Item;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Solution {
    public double capacity;
    public double value;
    public List<Item> itemList;
}
