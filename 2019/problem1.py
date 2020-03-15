conversions = [3, 16, 2, 2, 4]
units = ["TEASPOONS", "TABLESPOONS", "CUPS", "PINTS", "QUARTS", "GALLONS"]

n, s, t = input().split()
n = int(n)

from_unit = units.index(s)
to_unit = units.index(t)

while from_unit < to_unit:
  n //= conversions[from_unit]
  from_unit += 1

while to_unit < from_unit:
  from_unit -= 1
  n *= conversions[from_unit]

print(n)