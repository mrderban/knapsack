package Item;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.math3.util.Precision;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SmartItemFactory {

    public static double roundToOneDecimal(double d) {
        return Precision.round(d, 2);
    }


    //function using a memo table that computes the maximum value possible
    //given a weight limit and an input list of item
    public static void smartKnapSack(List<Item> inputItemList, double MAX_WEIGHT, double pace) {
        int NB_ITEMS = inputItemList.size();

        //compute the number of value increments given max weight allowed and pace chosen (starting from 0 to MAX_WEIGHT)
        int NB_VALUES = (int) (MAX_WEIGHT / pace);

        //grid initializing: we use it to store the best solutions so far (value + item list)
        //for the couples (items considered ; value limit) considered so far
        //[row][col]
        Solution[][] matrix = new Solution[NB_ITEMS + 1][NB_VALUES + 1];

        //first line (row 0) is filled with 0 (empty solutions)
        for (int j = 0; j <= NB_VALUES; j++)
            matrix[0][j] = Solution.builder()
                    .itemList(new ArrayList<>())
                    .value(0)
                    .build();

        //we iterate on each item in inputItemList
        for (int i = 1; i <= NB_ITEMS; i++) {
            //we iterate on each value increment
            for (int j = 0; j <= NB_VALUES; j++) {
                Item currentItem = inputItemList.get(i - 1);
                if (currentItem.getWeight() > j * pace) {
                    //can't add item => keep the best solution so far (cell just above)
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    //we need to check if we can improve the best solution so far by adding the current item
                    //best solution so far (currentBestSolution) for the given weight limit increment (cell just above)
                    Solution currentBestSolution = matrix[i - 1][j];

                    //start building a new candidate for 'best solution so far' given the current weight limit increment
                    //add current item value and add best value possible (so far) given the weight capacity left
                    Solution candidateBestSolution = Solution.builder()
                            .itemList(new ArrayList<>())
                            .value(roundToOneDecimal(currentItem.getValue() + matrix[i - 1][j - (int) (currentItem.getWeight() / pace)].getValue()))
                            .build();

                    //add current item to the best solution array list
                    candidateBestSolution.getItemList().add(currentItem);
                    //then add the item list of the best solution possible (so far) given the weight capacity left
                    candidateBestSolution.getItemList().addAll(matrix[i][j - (int) (currentItem.getWeight() / pace)].getItemList());

                    //compare candidateBestSolution & currentBestSolution
                    if (candidateBestSolution.getValue() > currentBestSolution.getValue()) {
                        matrix[i][j] = candidateBestSolution;
                    } else {
                        matrix[i][j] = currentBestSolution;
                    }

                }
            }

        }
        //display matrix
        for (Solution[] row : matrix) {
            for (Solution solution : row) {
                System.out.print("|");
                System.out.print(solution.getValue());
            }
            System.out.print("|" + "\n");
        }
        //TODO return last cell of matrix and extract list of items

    }
}



