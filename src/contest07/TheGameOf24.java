package contest07;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheGameOf24 {

  private static final String[] OPERATIONS = {" + ", " - ", " * ", " / "};

  public static void main(String[] args) {
    String[] input = new Scanner(System.in).nextLine().split(" ");
    List<Integer> values = new ArrayList<>();
    for (String str : input) {
      values.add(Integer.parseInt(str));
    }

    Expression expression24 = null;
    for (List<Integer> permutation : getPermutations(values)) {
      for (String operation1 : OPERATIONS) {
        for (String operation2 : OPERATIONS) {
          for (String operation3 : OPERATIONS) {
            List<String> operations = Arrays.asList(operation1, operation2, operation3);
            for (int rightParenthesis1 = 1; rightParenthesis1 < values.size(); ++rightParenthesis1) {
              for (int leftParenthesis1 = 0; leftParenthesis1 < rightParenthesis1; ++leftParenthesis1) {
                for (int rightParenthesis2 = 1; rightParenthesis2 < values.size(); ++rightParenthesis2) {
                  for (int leftParenthesis2 = 0; leftParenthesis2 < rightParenthesis2; ++leftParenthesis2) {
                    if (leftParenthesis1 != leftParenthesis2 || rightParenthesis1 != rightParenthesis2) {
                      Expression expression = makeExpression(
                          permutation,
                          operations,
                          Arrays.asList(
                              new AbstractMap.SimpleEntry<>(leftParenthesis1, rightParenthesis1),
                              new AbstractMap.SimpleEntry<>(leftParenthesis2, rightParenthesis2)
                          )
                      );
                      if (expression.getValue() == 24) {
                        expression24 = expression;
                        break;
                      }
                    }
                  }
                  if (expression24 != null) break;
                }
                if (expression24 != null) break;
              }
              if (expression24 != null) break;
            }
            if (expression24 != null) break;
          }
          if (expression24 != null) break;
        }
        if (expression24 != null) break;
      }
      if (expression24 != null) break;
    }

    System.out.println(expression24 != null ? expression24 : "No Solution");
  }

  public static <T> List<List<T>> getPermutations(List<T> list) {
    List<List<T>> permutations = new ArrayList<>();

    if (list.isEmpty()) {
      permutations.add(new ArrayList<>());
      return permutations;
    }

    T node = list.get(0);
    for (List<T> subPermutation : getPermutations(new ArrayList<>(list).subList(1, list.size()))) {
      for (int i = 0; i <= subPermutation.size(); ++i) {
        List<T> permutation = new ArrayList<>(subPermutation);
        permutation.add(i, node);
        permutations.add(permutation);
      }
    }

    return permutations;
  }

  public static Expression makeExpression(List<Integer> values, List<String> operations, List<Map.Entry<Integer, Integer>> parentheses) {
    List<String> numbers = values.stream().map(num -> num + "").collect(Collectors.toList());
    for (Map.Entry<Integer, Integer> parenthesis : parentheses) {
      numbers.set(parenthesis.getKey(), "(" + numbers.get(parenthesis.getKey()));
      numbers.set(parenthesis.getValue(), numbers.get(parenthesis.getValue()) + ")");
    }

    StringBuilder expression = new StringBuilder(numbers.get(0));
    for (int i = 0; i < operations.size(); ++i) {
      expression.append(operations.get(i)).append(numbers.get(i + 1));
    }
    return Expression.fromString(expression.toString());
  }

  interface Expression {
    double getValue();

    static Expression fromString(String str) {
      List<Expression> values = new ArrayList<>();
      List<Operation> operations = new ArrayList<>();

      int parentheses = 0;
      StringBuilder currentValue = new StringBuilder();
      for (char c : str.toCharArray()) {
        if (c == ')') {
          if (currentValue.length() > 0 && --parentheses == 0) {
            values.add(Expression.fromString(currentValue.toString()));
            currentValue = new StringBuilder();
          }
        }
        if (parentheses > 0 || Character.isDigit(c)) {
          currentValue.append(c);
        }
        if (c == '(') {
          ++parentheses;
        }

        if (parentheses == 0) {
          Operation operation = Operation.fromString(c + "");
          if (operation != null) {
            if (currentValue.length() > 0) {
              values.add(new ExtendedExpression.SingletonValue(Double.parseDouble(currentValue.toString())));
              currentValue = new StringBuilder();
            }

            operations.add(operation);
          }
        }
      }
      if (currentValue.length() > 0) {
        values.add(new ExtendedExpression.SingletonValue(Double.parseDouble(currentValue.toString())));
      }

      if (values.size() == 1) {
        return values.get(0);
      }
      return new ExtendedExpression(values, operations);
    }
  }

  static class ExtendedExpression implements Expression {
    private final List<Expression> values;
    private final List<Operation> operations;

    public ExtendedExpression(List<Expression> values, List<Operation> operations) {
      this.values = values;
      this.operations = operations;
    }

    public double getValue() {
      List<Operation> operations = new ArrayList<>(this.operations);
      List<Expression> values = new ArrayList<>(this.values);

      for (int i = 0; i < operations.size(); ++i) {
        Operation operation = operations.get(i);
        if (operation.equals(Operation.MULTIPLICATION) || operation.equals(Operation.DIVISION)) {
          double result = operation.applyTo(values.get(i).getValue(), values.get(i + 1).getValue());
          operations.remove(i);
          values.remove(i + 1);
          values.remove(i);
          values.add(i, new SingletonValue(result));
          --i;
        }
      }
      for (int i = 0; i < operations.size(); ++i) {
        double result = operations.get(i).applyTo(values.get(i).getValue(), values.get(i + 1).getValue());
        operations.remove(i);
        values.remove(i + 1);
        values.remove(i);
        values.add(i, new SingletonValue(result));
        --i;
      }
      return values.get(0).getValue();
    }

    @Override
    public String toString() {
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < values.size(); ++i) {
        boolean nested = values.get(i) instanceof ExtendedExpression;
        str.append(nested ? "(" : "").append(values.get(i)).append(nested ? ")" : "");
        if (i < operations.size()) {
          str.append(operations.get(i).toPaddedString());
        }
      }
      return str.toString();
    }

    static class SingletonValue implements Expression {
      private double value;

      public SingletonValue(double value) {
        this.value = value;
      }

      @Override
      public double getValue() {
        return value;
      }

      @Override
      public String toString() {
        return (int) value + "";
      }
    }
  }

  enum Operation {
    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/");

    private String sign;

    Operation(String sign) {
      this.sign = sign;
    }

    @Override
    public String toString() {
      return sign;
    }

    public String toPaddedString() {
      return " " + toString() + " ";
    }

    static Operation fromString(String str) {
      for (Operation operation : Operation.values()) {
        if (operation.toString().equals(str)) {
          return operation;
        }
      }
      return null;
    }

    public double applyTo(double num1, double num2) {
      switch (this) {
        case ADDITION: return num1 + num2;
        case SUBTRACTION: return num1 - num2;
        case MULTIPLICATION: return num1 * num2;
        case DIVISION: return num1 / num2;
        default: return 0;
      }
    }
  }

}
