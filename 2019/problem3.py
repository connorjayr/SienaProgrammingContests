n, b = [int(i) for i in input().split()]

converted = []
while n > 0:
  converted.append(n % b)
  print(n // b, n % b)
  n //= b

print("".join(["0123456789ABCDEF"[d] for d in reversed(converted)]))