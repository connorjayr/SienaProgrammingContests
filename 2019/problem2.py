def to_digits(n: int) -> tuple:
  digits = []
  while n > 0:
    digits.append(n % 10)
    n //= 10
  return tuple(reversed(digits))

n = int(input())
steps = 0
while n != 1 and n != 89:
  n = sum([digit ** 2 for digit in to_digits(n)])
  steps += 1

print(n, steps)